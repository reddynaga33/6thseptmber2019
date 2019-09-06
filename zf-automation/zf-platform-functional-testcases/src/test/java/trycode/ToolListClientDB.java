package trycode;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import com.openmatics.testinstrumentation.utils.sql.DbQueryService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ToolListClientDB {

    public static void main(String[] args) throws Exception {
        String envKey = "daedalus";
        String substringContained = "automated";
        IEnvironmentConfiguration configuration = new EnvironmentConfiguration(envKey);
        PlatformInstrumentation inst = new PlatformInstrumentation(configuration);
        ClientManagementApi cms = new ClientManagementApi(envKey);
        StringBuilder strClientIds = new StringBuilder("(" + System.lineSeparator());


        HashMap<String, String> map = new HashMap<>();
        HttpResponse response = cms.getClientApi().getClients();
        DbConnectionFactory dbConnectionFactory = new DbConnectionFactory(inst.getEnvConf().getDbServerConnectionString());
        if (response.getStatus() == 200){
            DbQueryService dbAccess = new DbQueryService(dbConnectionFactory);
            JSONArray clients = response.getContentAsJsonArray();
            for(Object item : clients){
                JSONObject client = (JSONObject) item;
                String name = client.getString("name");
                System.out.println(name);
                //strClientIds.append("--"
                strClientIds.append(String.format("'client-%s-db',%s", client.getString("id"), System.lineSeparator()));
                /**/
                if (name.toLowerCase().contains(substringContained)){
                    String clientId = client.getString("id");
                    String query = String.format("SELECT * FROM master.dbo.sysdatabases where name = 'client-%s-db'", clientId);
                    ResultSet rs = dbAccess.executeQuery(query);
                    if (rs.next()) {
                        map.put(name, String.format("DROP DATABASE IF EXISTS [%s]", rs.getString("name")));
                    }
                    dbAccess.closeQuery(rs);
                }
            }
        }
        strClientIds.append(String.format("'client-%s-db')", "ee986775-b80c-4cd5-9858-359d61a5cf12"));
        System.out.println(strClientIds);


        /**/
        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println("GO");
            System.out.println("-- client: " + entry.getKey());
            System.out.println( entry.getValue());
            System.out.println("GO");
        }
        dbConnectionFactory.closeConnection();
     }
}
