package trycode;

import com.microsoft.azure.servicebus.Message;
import com.openmatics.test.functional.platform.client.delete.CleanClientsReadFromDbDataProvider;
import com.openmatics.test.functional.platform.servicemanagement.SwitchTestUtil;
import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.utils.ServiceBusConnectionConfiguration;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;

import java.sql.ResultSet;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TryMain {

    public static void main(String[] args) throws Exception {
        String envKey = "tauri";
        String clientId = "8870965f-d5cd-4d2e-a2f9-71eeb65ba2fd";
        String assetId = "414ee198-77ab-49c5-b58a-e877198c240a";
        EnvironmentConfiguration env = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation instrumentation = new PlatformInstrumentation(env);

        String value = "aaa"; //DeploymentContext.getEventServiceStarted("363636");
        System.out.println(value);
        /*
        CleanClients cleanClients = new CleanClients(instrumentation,null, null);
        List<Asset> assets = cleanClients.getClientAssets(clientId);
        assets = assets.stream()
                .filter(asset -> asset.getId().equalsIgnoreCase(assetId))
                .collect(Collectors.toList());
        assets.stream().forEach(asset -> cleanClients.removeClientFromAsset(asset, clientId));
        */


    }

    public static void mainDate(String[] args) throws Exception {
        int older = 7;
        String date = "2019-03-25T20:25:08.849Z";
        /*
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        c.setTime(sdf1.parse(date));// all done
        c.add(7, Calendar.DATE);
        boolean isBefore = c.before(new Date());
        */
        Instant in = Instant.parse(date);
        Instant added = in.plus(7, ChronoUnit.DAYS);
        boolean isBefore = added.isBefore(Instant.now());
        CleanClientsReadFromDbDataProvider provider = new CleanClientsReadFromDbDataProvider();



    }


    public static void mainXXXX(String[] args) throws Exception {

        // Restore destonation for switch mode singlClient
        EnvironmentConfiguration env = new EnvironmentConfiguration("daedalus");
        ServiceBusConnectionConfiguration sbConf = env.getServiceBusProperty();
        ServiceBusClient sbClient = new ServiceBusClient(sbConf.getConnectionString());

        String deleteSyntetic = "delete [deployment_to_ev_dest]\n" +
                "from [service_management_service_schema].[deployment_to_ev_dest]\n" +
                "join [service_management_service_schema].[event_destination]\n" +
                "on [event_destination].id=[deployment_to_ev_dest].[event_destination_id]\n" +
                "where name_identifier like 'com.openmatics.test.sms_switch%'\n" +
                "or name_identifier like 'com.openmatics.test.test_topic%'";

        // delete all syntetic topic
        DbConnectionFactory dbConnectionFactory = new DbConnectionFactory(env.getDbConnectionString());
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            int affectedRow = sqlAccess.executeUpdate(deleteSyntetic);
            System.out.println("affectedRow: " + affectedRow);
        }

        String query = "some query";//DeploymentContext.getSqlQuery("multiclient-test-service");
        try (DbQueryService sqlAccess = new DbQueryService(dbConnectionFactory)) {
            ResultSet rs = sqlAccess.executeQuery(query);
            while(rs.next()){
                if(rs.getString("deployment_status").equals("DEPLOYED")){
                    Message sbMessage = new Message(SwitchTestUtil.getEventServiceStarted(rs.getString("id")));
                    sbClient.sendToTopic("com.openmatics.cloud.core.service_service-management-service_servicestarted", sbMessage);
                }
            }
            sqlAccess.closeQuery(rs);
        }
        dbConnectionFactory.closeConnection();
    }

    public static void main1(String[] args) throws Exception {

        IEnvironmentConfiguration env = new EnvironmentConfiguration("tauri");

        ServiceBusClient sb = new ServiceBusClient(env.getServiceBusProperty().getConnectionString());
        //AutomatedTestExplore
        sb.sendToTopic("AutomatedTestExplore", new Message());
        //sb.send2TopicAsync("AutomatedTestExplore");
        System.out.println("After sending.");
        return;

    }

    public static void mainOld(String[] args) throws Exception {

        String clientId = "43f837e9-8a71-4964-a5b9-6b0bbcb5fb99";
        String name = String.format("client-%s-db", clientId);
        System.out.println(name);

        /* regex*/
        String value = "jedna${client}dva";
        Boolean isMatch = value.matches("jedna(.*\\$\\{.*\\}.*)");
        System.out.println("isMatch: " + isMatch);
        Pattern pattern = Pattern.compile("\\$\\{(.*)\\}");
        Matcher m = pattern.matcher(value);
        while (m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group());
            if ( m.groupCount() > 2 )System.out.println(m.group(2));
        }


    }


}
