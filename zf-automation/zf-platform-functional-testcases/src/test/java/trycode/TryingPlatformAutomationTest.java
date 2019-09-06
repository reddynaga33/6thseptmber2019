package trycode;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Test
public class TryingPlatformAutomationTest {

    private String envKey;
    private String clientSuffix;


    @BeforeClass()
    @Parameters({"envKey","clientSuffix"})
    public void before(@Optional("ares") String envKey, String clientSuffix){
        this.envKey = envKey;
        this.clientSuffix = clientSuffix;
    }

    @Test()
    public void run() throws Exception {

        String fileName = String.format("log_%s_%s.devel.txt", envKey, clientSuffix);
        java.nio.file.Path path = java.nio.file.Paths.get(fileName);
        //File file = new File(fileName);
        //file.setWritable(true);
        java.nio.file.Files.write(path, fileName.getBytes("UTF-8"));
    }

    @Ignore
    @Test()
    public void gremlin() throws Exception {

        String GREMLIN_CONN_STRING_TEMPLATE_PATH = "src/test/resources/environment/daedalus/gremlin.yaml";

        String connectionConfigFile = GREMLIN_CONN_STRING_TEMPLATE_PATH;
        Cluster cluster;
        Client client;
        cluster = Cluster.open(connectionConfigFile);
        client = cluster.connect();
        String query=String.format("g.V()");
        List<Result> resultList = null;
        try
        {
            ResultSet results = client.submit(query);
            CompletableFuture<List<Result>> completableFutureResults = results.all();
            resultList = completableFutureResults.get(20, TimeUnit.SECONDS);
            for (Result result : resultList) {
                System.out.println("Query result:");
                System.out.println(result.toString());
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            if (client != null) client.close();
        }

    }

}
