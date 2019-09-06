package com.openmatics.testinstrumentation.automotive.cases.realtimedata;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.openmatics.testinstrumentation.automotive.AutomotiveInstrumentation;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;
import com.openmatics.testinstrumentation.utils.iothub.DeviceMessageCallback;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import org.json.JSONObject;
import org.testng.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class SendRtsDataMessagesCloud2Device {

    private final String STOP_COLLECT_REQUEST_TEMPLATE = "/realTimeData/stopCollectingData?key=%s";
    private final String START_COLLECT_REQUEST_TEMPLATE = "/realTimeData/startCollectingData?vehicleId=%s&signalNames=velocity&signalNames=engineSpeed";
    private final DbConnectionFactory connectionFactory;
    private PlatformInstrumentation platformInstrumentation;
    private AutomotiveInstrumentation automotiveInstr;
    private RestApiBase apiBase;
    private Map<String, JSONObject> devices;
    private Map<String, JSONObject> vehicles;

    public SendRtsDataMessagesCloud2Device(DbConnectionFactory connectionFactory, PlatformInstrumentation platformInstrumentation, AutomotiveInstrumentation automotiveInstr) throws Exception {
        this.connectionFactory = connectionFactory;
        this.platformInstrumentation = platformInstrumentation;
        this.automotiveInstr = automotiveInstr;
        IServiceConfiguration property = new ServiceConfiguration("real-time-data-service", platformInstrumentation.getEnvConf());
        apiBase = new RestApiBase(this.platformInstrumentation.getClientConf().getClient().getId(), property);
    }

    public void givenDevice4Sending() throws Exception
    {
        vehicles = automotiveInstr.getVehicleConf().loadVehicles(true,1);
        devices = platformInstrumentation.getDeviceConf().loadDevices(vehicles);
        System.out.println("Device for message sending.");
        System.out.println(devices);
    }

    public void SendMessagesCloud2Device() throws Exception
    {
        if (vehicles.size() != devices.size()) throw new RuntimeException("Count of vehicels and devices are diferend");
        JSONObject device = devices.get(devices.keySet().toArray()[0]);
        String connStringTemplate = SendRtsDataMessagesDevice2Cloud.DEVICE_CONNECTION_STRING_TEMLATE;
        connStringTemplate = connStringTemplate.replace("${HostName}", platformInstrumentation.getEnvConf().getIotHubHostName());
        String connString = connStringTemplate.replace("${DeviceId}", device.getString("boxId"));
        connString = connString.replace("${SharedAccessKey}", device.getString("primaryKey"));
        System.out.println("Device connection string");
        System.out.println(connString);
        DeviceClient deviceClient = new DeviceClient(connString, IotHubClientProtocol.AMQPS);
        DeviceMessageCallback callback = new DeviceMessageCallback();
        deviceClient.setMessageCallback(callback, null);
        deviceClient.open();

        String requestId = sendStartCollectRequest(device);
        Boolean isOk = false;
        int timeOutMs = 10000;
        int timeSleepMs = 1000;
        int timeMs = 0;
        while (timeMs <  timeOutMs && !isOk){
            System.out.println(String.format("Waiting for receive messages %s/%s", (timeMs + timeSleepMs), timeOutMs));
            Thread.sleep(timeSleepMs);
            timeMs += timeSleepMs;
            if (callback.getMessages().size() > 0){
                isOk = checkMessages(callback.getMessages(), requestId);
            }
        }

        sendStopCollectRequest(requestId);
        deviceClient.closeNow();

        if (!isOk) Assert.fail(String.format("Message (collect request) from coud did't receive with device boxId %s, assetId %s.", device.getString("boxId"), device.getString("assetId")));

    }

    private Boolean checkMessages(List<String> messages, String requestId) throws Exception {
        // load request from DB by the request id and get those timestamp
        // and compare it with time from message
        Instant dbDate;
        Instant messageDate;
        String query = String.format("select last_timestamp_raw from [real_time_data_service_schema].[vehicle_request] where id=%s", requestId);
        try {
            DbQueryService sql = new DbQueryService(connectionFactory);
            ResultSet rs = sql.executeQuery(query);
            if(!rs.next()) throw new RuntimeException("It was expected record for requestId=" + requestId);
            System.out.println("from db: " + rs.getLong("last_timestamp_raw"));
            dbDate = Instant.ofEpochMilli(rs.getLong("last_timestamp_raw"));
            sql.closeQuery(rs);
        } catch (SQLException e) {
            System.err.println("The database operation can be executed due to: " + e.getMessage());
            throw new RuntimeException("The database operation can be executed due to " + e.getMessage());
        }
        Boolean isOk=false;
        for(String message : messages){
            if (message.contains("realtimedataservice.SignalDataRequest")){
                JSONObject jsonMessage = new JSONObject(message);
                String srtMessageDate = jsonMessage.getJSONObject("payload").getString("requestTime");
                srtMessageDate = srtMessageDate.replace("T"," ");
                System.out.println("String time from message: " + srtMessageDate);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("UTC"));
                messageDate = df.parse(srtMessageDate).toInstant();

                System.out.println("Compare date " + messageDate + " -------{");
                System.out.println("Is before date" + dbDate.plusSeconds(2));
                System.out.println("Is after date" + dbDate.minusSeconds(2));

                if (messageDate.isBefore(dbDate.plusSeconds(2)) && messageDate.isAfter(dbDate.minusSeconds(2))){
                    System.out.println("IsOk");
                    isOk = true;
                }
                System.out.println("}Compare date end ------- ");
            }
        }
        return isOk;

    }

    private String sendStartCollectRequest(JSONObject device) throws Exception {
        String request = String.format(START_COLLECT_REQUEST_TEMPLATE, device.getString("assetId"));
        HttpResponse response = apiBase.getGetResponse(request);
        String requestId = response.getContentAsString();
        System.out.println("RequestId: " + requestId);
        return requestId;
    }

    private void sendStopCollectRequest(String requestId) throws Exception {
        String request = String.format(STOP_COLLECT_REQUEST_TEMPLATE, requestId);
        apiBase.getPostResponse(request,"");
    }
}
