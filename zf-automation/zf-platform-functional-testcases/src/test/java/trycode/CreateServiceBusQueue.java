package trycode;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.utils.ServiceBusConnectionConfiguration;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusDestinationsHandler;
import trycode.handler.BasicHandler;
import trycode.handler.ServiceBusExampleTest;

public class CreateServiceBusQueue {
    private static final String PAYLOAD = "testing message";
    private static ServiceBusDestinationsHandler destinationsHandler;
    private static ServiceBusClient serviceBusClient;
    private static ServiceBusExampleTest serviceBusExampleTest;
    private static BasicHandler handler = new BasicHandler();


    public static void main(String[] args) throws Exception {
        IEnvironmentConfiguration env = new EnvironmentConfiguration("daedalus");
        ServiceBusConnectionConfiguration connectionConfiguration = env.getServiceBusProperty();
        destinationsHandler = new ServiceBusDestinationsHandler(connectionConfiguration);
        serviceBusClient = new ServiceBusClient(connectionConfiguration.getConnectionString());
        String queueName = "platform-test";
        serviceBusExampleTest = new ServiceBusExampleTest(destinationsHandler, serviceBusClient, handler);
        initQueue(queueName);

        serviceBusExampleTest.receiveMessage(queueName);
        serviceBusExampleTest.sendMessage(queueName, PAYLOAD);

        serviceBusExampleTest.waitForReceiving(1);

        serviceBusExampleTest.compare(PAYLOAD);
        serviceBusExampleTest.close();
        serviceBusExampleTest.deleteQueue(queueName);
        System.out.println(0);
    }

    private static void initQueue(String queueName) {
        serviceBusExampleTest.createQueue(queueName);
        destinationsHandler.isQueueExists(queueName);
    }


}
