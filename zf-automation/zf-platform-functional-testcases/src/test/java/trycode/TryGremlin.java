package trycode;

import com.openmatics.testinstrumentation.platform.service.global.deviceconfigurationhierarchy.DeviceConfigurationHierarchyConfiguration;
import org.apache.tinkerpop.gremlin.driver.*;

import java.util.*;
import java.util.concurrent.*;

public class TryGremlin {

    public static void main(String[] args) throws Exception {
        String fileFullName = "src/test/resources/trycode/try_gremlin.yaml";
        Cluster cluster;
        Client client;

        /*
        org.apache.commons.CmsClientConfiguration.CmsClientConfiguration conf;// = org.apache.commons.CmsClientConfiguration.CmsClientConfiguration.
        FileInputStream fs = new FileInputStream("src/test/resources/platform/gremlin.yaml");
        YAMLConfiguration conf2 = new YAMLConfiguration();
        java.io.FileReader fr = new java.io.FileReader(fileFullName);
        conf2.read(fr);
        //username: /dbs/test/colls/tabor
        conf2.setProperty("username","/dbs/test/colls/tabor");
        Builder builder = Cluster.build();
        builder.credentials(conf2.getString("username"),conf2.getString("password"));
        //daedalus-gremlin-db.gremlin.cosmosdb.azure.com
        builder.addContactPoint("daedalus-gremlin-db.gremlin.cosmosdb.azure.com");
        //builder.addContactPoints(conf2.getString("hosts"));
        builder.port(Integer.parseInt(conf2.getString("port")));
        //serializer: { className: org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerV1d0, config: { serializeResultToString: true }}
        org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerV1d0 serializerV1d0 = new org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerV1d0();
        final Map<String, Object> m = new HashMap<>();
        m.put("serializeResultToString", true);
        serializerV1d0.configure(m, null);
        */

        cluster = Cluster.open(fileFullName);
        ExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        //executor.awaitTermination(15,TimeUnit.SECONDS);
        Callable<Client> callable = new Callable<Client>() {
            @Override
            public Client call() {
                return cluster.connect();
            }
        };
        System.out.println("1");
        Future<Client> future = executor.submit(callable);
        client = future.get();
        executor.shutdown();

        String query = "g.V('Dusin')";

        try {
            ResultSet results = client.submit(query);
            System.out.println("5");
            CompletableFuture<List<Result>> completableFutureResults = results.all();
            List<Result> resultList = completableFutureResults.get(1, TimeUnit.SECONDS);
            for (Result result : resultList) {
                System.out.println("\nQuery result:");
                System.out.println(result.toString());
                System.out.println("\n--- next ---:");
                System.out.println(result.getClass());
                LinkedHashMap configuration = (LinkedHashMap)result.getObject();
                LinkedHashMap configurationProperties = (LinkedHashMap) configuration.get("properties");
                System.out.println(DeviceConfigurationHierarchyConfiguration.getValueOfVertexProperty(configurationProperties, "firstName"));
                System.out.println(DeviceConfigurationHierarchyConfiguration.getValueOfVertexProperty(configurationProperties, "age"));
                System.out.println("----");
                for (Object key : configurationProperties.keySet()) {

                    System.out.println("Property name: " + key);
                    //System.out.println(configurationProperties.get(key).getClass());
                    ArrayList list = (ArrayList) configurationProperties.get(key);
                    for(int i = 0 ; i < list.size(); i++){
                        //System.out.println(list.get(i).getClass());
                        LinkedHashMap values = (LinkedHashMap) list.get(i);
                        for (Object valueName : values.keySet()) {
                            System.out.println(valueName + " : " + values.get(valueName));
                        }
                    }
                    System.out.println("----");

                }
                System.out.println("---- end -----");

                System.out.println(configuration.get("id"));
                System.out.println(configuration.get("label"));
                System.out.println(configurationProperties.get("age"));

            }
        }
        catch (Exception e){

            //e.printStackTrace();
            System.out.println("********************************************");
            System.out.println(e.getMessage());
            System.out.println("********************************************");
            System.out.println(e);
            System.out.println("********************************************");
            e.printStackTrace(System.out);

        }
        finally {
            System.out.println("finally");
            if (client != null) client.close();
        }

        System.exit(0);
    }
}
