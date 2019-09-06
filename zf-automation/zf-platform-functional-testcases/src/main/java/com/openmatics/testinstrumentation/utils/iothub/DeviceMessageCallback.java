package com.openmatics.testinstrumentation.utils.iothub;

import com.microsoft.azure.sdk.iot.device.IotHubMessageResult;
import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.MessageCallback;

import java.util.*;

public class DeviceMessageCallback implements MessageCallback {

    private List<String> messages = new ArrayList<String>();

    public List<String> getMessages(){
        return messages;
    }

    public IotHubMessageResult execute(Message msg, Object context) {
        String message = new String(msg.getBytes(), Message.DEFAULT_IOTHUB_MESSAGE_CHARSET);
        messages.add(message);
        System.out.println("Received message from hub: " + message);
        System.out.println("Count of receive massage is: "  + messages.size());
        return IotHubMessageResult.COMPLETE;
    }

}
