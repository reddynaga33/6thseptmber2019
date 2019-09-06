package com.openmatics.testinstrumentation.automotive.cases.realtimedata;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.Message;
import com.openmatics.testinstrumentation.automotive.AutomotiveInstrumentation;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import com.openmatics.testinstrumentation.utils.iothub.MessageSender;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import com.openmatics.testinstrumentation.utils.sql.SqlUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//clean test -DclientSuffix=AA -DenvKey=daedalus -DsuiteXmlFile=src/test/suite/platform/client/CreateClientInfrastructure.xml -s ~/.m2/settings_internal.xml

public class SendRtsDataMessagesDevice2Cloud {

    public final static String DEVICE_CONNECTION_STRING_TEMLATE ="HostName=${HostName};DeviceId=${DeviceId};SharedAccessKey=${SharedAccessKey}";
    private final String SQL_CHECK_QUERY_TEMPLATE = "SELECT * FROM [real_time_data_service_schema].[signal_value] where vehicle_id = '%s' and last_timestamp = '%s'";
    private String sourceMessageTemplate = "automotive/RealTimeDataMessageTemplate.json";
    private PlatformInstrumentation platformInstr;
    private AutomotiveInstrumentation automotiveInstr;
    private String clientId;
    private Map<String, JSONObject> devices;
    private Map<String, JSONObject> vehicles;
    private Instant nowTime;
    private List<Check> checks;
    private final DbConnectionFactory connectionFactory;

    private class Check {
        public JSONObject message;
        public String assedId;
        public Boolean IsOK = false;
        public String reason = null;

        public Check(String assedId, JSONObject message) {
            this.message = message;
            this.assedId = assedId;
        }
    }


    public SendRtsDataMessagesDevice2Cloud(PlatformInstrumentation PlatformInstrumentation, AutomotiveInstrumentation automotiveInstr, DbConnectionFactory connectionFactory) throws Exception {
        this.platformInstr = PlatformInstrumentation;
        this.automotiveInstr = automotiveInstr;
        this.connectionFactory = connectionFactory;
        this.clientId = this.platformInstr.getClientConf().getClient().getId();
    }

    public void givenDevice4Sending() throws Exception {
        vehicles = automotiveInstr.getVehicleConf().loadVehicles(true);
        devices = platformInstr.getDeviceConf().loadDevices(vehicles);
        System.out.println("Device for message sending.");
        System.out.println(devices);
    }

    public void whenISendMessagesDevice2Cloud() throws Exception {

        if (vehicles.size() != devices.size()) throw new RuntimeException("Count of vehicels and devices are diferend");

        int timeOut4send = 60;
        //load message template
        String messageTemplate = ResourceLoader.LoadAsString(sourceMessageTemplate);
        String connStringTemplate = DEVICE_CONNECTION_STRING_TEMLATE;
        connStringTemplate = connStringTemplate.replace("${HostName}", platformInstr.getEnvConf().getIotHubHostName());
        nowTime = Instant.now();
        messageTemplate = messageTemplate.replace("${timestamp}", nowTime.toString());
        messageTemplate = messageTemplate.replace("${latitude}", "49.811760167");
        messageTemplate = messageTemplate.replace("${longitude}", "13.400311667");
        checks = new ArrayList<Check>();
        // foreach devices and send
        int speed = 0;
        for (Map.Entry<String, JSONObject> entry : devices.entrySet()) {
            speed++;
            // TODO rename boxId to deviceId also in json template
            String assetId = entry.getValue().getString("assetId");
            JSONObject jsonMessage = new JSONObject(messageTemplate.replace("${speed}", String.valueOf(speed)));
            Message message = new Message(jsonMessage.toString());
            String connString = connStringTemplate.replace("${DeviceId}", entry.getKey());
            connString = connString.replace("${SharedAccessKey}", entry.getValue().getString("primaryKey"));
            DeviceClient deviceClient = new DeviceClient(connString, IotHubClientProtocol.AMQPS); // AMQPS
            System.out.println(String.format("To asset %s Will be send message:", assetId));
            System.out.println(jsonMessage);
            checks.add(new Check(assetId, jsonMessage));

            // send message to cloud
            deviceClient.open();
            MessageSender sender = new MessageSender(message, deviceClient);

            ExecutorService executor = Executors.newFixedThreadPool(1);
            executor.execute(sender);
            executor.shutdown();
            executor.awaitTermination(timeOut4send, TimeUnit.SECONDS);
            deviceClient.closeNow();
        }
    }


    public void thenMessagesAreStoredInDb() throws Exception {
        int timeOutMs = 10000;
        int timeSleepMs = 1000;
        int timeMs = 0;


        while (timeMs < timeOutMs) {
            Boolean isOkAll = true;
            for (Check check : checks) {
                if (!check.IsOK) {
                    String query = String.format(SQL_CHECK_QUERY_TEMPLATE, check.assedId, SqlUtils.getTimeString(nowTime));
                    try {
                        DbQueryService sql = new DbQueryService(connectionFactory);
                        ResultSet rs = sql.executeQuery(query);
                        executeCheck(check, rs);
                        sql.closeQuery(rs);
                    } catch (SQLException e) {
                        System.err.println("The database operation can be executed due to: " + e.getMessage());
                        throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
                    }

                    if (!check.IsOK) {
                        System.out.println(String.format("waiting for processed messages %s/%s", (timeMs + timeSleepMs), timeOutMs));
                        Thread.sleep(timeSleepMs);
                        timeMs += timeSleepMs;
                        isOkAll = false;
                        break;
                    }
                }
            }
            if (isOkAll) break;
        }

        isOkAssert(checks);
    }


    private void isOkAssert(List<Check> checks) {
        List<String> reasons = new ArrayList<String>();
        for (Check check : checks) {
            if (!check.IsOK) {
                reasons.add(String.format("For assetId %s and timestamp %s is reason: %s", check.assedId, nowTime, check.reason));
            }
        }
        if (reasons.size() > 0) Assert.fail(reasons.toString());
    }


    private void executeCheck(Check check, ResultSet rs) throws Exception {
        HashMap<String, String> results = new HashMap<>();

        while (rs.next()) {
            results.put(rs.getString("signal_name"), rs.getString("value"));
        }

        if (results.size() != 3) {
            check.reason = String.format("Records count in table RTS table signal_values is %s but expected is 3", results.size());
            check.IsOK = false;
            return;
        }

        for (String signal_name : results.keySet()) {
            executeValuesAssert(signal_name, results.get(signal_name), check.message.getJSONObject("payload").getJSONArray("data"));
        }

        check.IsOK = true;
    }


    private void executeValuesAssert(String signal_name, String value, JSONArray data) throws Exception {
        switch (signal_name) {
            case "gps.latitude":
                String expectedLatitude = JsonUtils.getJsonItem(data, "signalCode", "18").getString("value");
                Assert.assertEquals(value, expectedLatitude);
                break;
            case "gps.longitude":
                String expectedLongitude = JsonUtils.getJsonItem(data, "signalCode", "19").getString("value");
                Assert.assertEquals(value, expectedLongitude);
                break;
            case "WheelBasedVehicleSpeed":
            case "velocity":
                String expectedVelocity = JsonUtils.getJsonItem(data, "signalCode", "61").getString("value");
                Assert.assertEquals(value, expectedVelocity);
                break;
            default:
                Assert.fail(String.format("Unknow signal name %s", signal_name));
        }
    }
}
