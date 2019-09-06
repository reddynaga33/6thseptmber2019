package trycode.handler;

import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusClient;
import com.openmatics.testinstrumentation.utils.azure.ServiceBusDestinationsHandler;
import com.openmatics.testinstrumentation.utils.azure.exception.DestinationHandlerException;

import java.util.concurrent.TimeUnit;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 28.02.2019.
 */
public class ServiceBusExampleTest {
    private ServiceBusDestinationsHandler destinationsHandler;
    private ServiceBusClient serviceBusClient;
    private BasicHandler handler;
    private IQueueClient client;

    public ServiceBusExampleTest(ServiceBusDestinationsHandler destinationsHandler, ServiceBusClient serviceBusClient, BasicHandler handler) {
        this.destinationsHandler = destinationsHandler;
        this.serviceBusClient = serviceBusClient;
        this.handler = handler;
    }

    public synchronized void waitForReceiving(int messageCount) {
        try {
            while (handler.getStorage().size() < messageCount) {
                TimeUnit.MILLISECONDS.timedWait(this, 1);
                System.out.println("WAITING");
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted exception: " + e.getMessage());
        }
        System.out.println("Waiting end");
    }

    public void sendMessage(String queueName, String payload) {
        serviceBusClient.sendToQueue(queueName, new Message(payload));
    }

    public void receiveMessage(String queueName) {
        client = serviceBusClient.receiveQueueMessage(queueName, handler);
    }

    public void createQueue(String queueName) {
        try {
            destinationsHandler.ensureQueueCreation(queueName);
        } catch (DestinationHandlerException e) {
            System.err.println("Creation queue error:" + e.getMessage());
        }
    }

    public void deleteQueue(String queueName) {
        destinationsHandler.deleteQueue(queueName);
    }

    public void compare(String payload) {
        String current = new String(handler.getStorage().get(0).getMessageBody().getBinaryData().get(0));
        if (!current.equals(payload)) {
            System.err.println("The body is not equaled: " + current + " = " + payload);
        } else {
            System.out.println(" Compating is ok: " + current + " = " + payload);
        }
    }

    public void close() {
        try {
            client.close();
        } catch (ServiceBusException e) {
            System.err.println("CmsClient can't be closed");
        }
    }
}
