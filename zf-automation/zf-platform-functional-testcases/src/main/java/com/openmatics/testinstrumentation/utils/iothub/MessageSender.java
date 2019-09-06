package com.openmatics.testinstrumentation.utils.iothub;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.Message;

/*
    Project example:
    //https://docs.microsoft.com/cs-cz/azure/iot-hub/iot-hub-java-java-c2d
*/

public class MessageSender implements Runnable {

    private Message message;
    private DeviceClient client;

    public MessageSender(Message message, DeviceClient client){
        this.message = message;
        this.client = client;
    }

    public void run() {
        try {

            System.out.println("Will be send message:\n" + message.toString());
            Object lockobj = new Object();
            EventCallback callback = new EventCallback();
            client.sendEventAsync(message, callback, lockobj);

            synchronized (lockobj) {
                lockobj.wait();
            }

        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException." + e.getMessage());
        }
    }

}
