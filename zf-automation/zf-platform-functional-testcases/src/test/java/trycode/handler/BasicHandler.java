package trycode.handler;

import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 28.02.2019.
 */
public class BasicHandler implements IMessageHandler {
    private List<IMessage> storage = new ArrayList<>();


    @Override
    public CompletableFuture<Void> onMessageAsync(IMessage message) {
        System.out.println("Receive message");
        storage.add(message);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void notifyException(Throwable exception, ExceptionPhase phase) {
        System.err.println("Error in message handler: " + exception.getMessage());
    }

    public List<IMessage> getStorage() {
        return storage;
    }

}
