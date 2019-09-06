package com.openmatics.test.functional.platform.servicemanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 26.04.2019.
 */
public final class SwitchTestUtil {
    public final static String DESTINATIONS_PREFIX = "com.openmatics.test.sms_switch";
    public final static String EVENT_EVENT_TYPE_NAME = "com.openmatics.cloud.communication.DeviceStateEvent";
    public final static String TOPIC_EVENT_TYPE_NAME = "com.openmatics.test.testEvent";
    public final static String EVENT_FIRST = DESTINATIONS_PREFIX + "." + "test_eventfirst";
    public final static String EVENT_SECOND = DESTINATIONS_PREFIX + "." + "test_eventsecond";
    public final static String TOPIC_FIRST = DESTINATIONS_PREFIX + "." + "test_topicfirst";
    public final static String TOPIC_SECOND = DESTINATIONS_PREFIX + "." + "test_topicsecond";

    private SwitchTestUtil() {
    }

    public static List<String> getSyntheticDestinationName() {
        List<String> syntheticDestinationNames = new ArrayList<>();
        syntheticDestinationNames.add(EVENT_FIRST);
        syntheticDestinationNames.add(EVENT_SECOND);
        syntheticDestinationNames.add(TOPIC_FIRST);
        syntheticDestinationNames.add(TOPIC_SECOND);
        return syntheticDestinationNames;
    }

    public Boolean isDestinationSynthetic(String destinationName) {
        boolean match = getSyntheticDestinationName().stream().anyMatch(name -> name.equalsIgnoreCase(destinationName));
        return match;
    }

    public static String getTestEvent(String eventId, String eventTimestemp, String clientId, String eventUUID) {
        //TODO: it is not cool - The SMS will be removed soon. Refactoring is waste of the time
        String testEventTemplate = "{" +
                "\"payload\": {" +
                "\"operation\": \"CREATE\"," +
                "\"data\": {" +
                "\"id\": \"XXX57000027\"" +
                "}," +
                "\"errorDescription\": null" +
                "}," +
                "\"eventID\": \"${eventId}\"," +
                "\"eventTimestamp\": \"${eventTimestamp}\"," +
                "\"sourceName\": \"com.openmatics.cloud.core.service_device-management-service\"," +
                "\"causeEventID\": \"dc32d2d8-7330-4425-8a6d-7a39af0752b8\"," +
                "\"clientId\": \"${clientId}\"," +
                "\"eventUUID\": \"${eventUUID}\"" +
                "}";
        String innerEventTimestemp = "2018-09-27T09:12:45.663Z";
        String innerEventId = "ff0a7f81-7e00-4d69-bca6-2acc15261855";
        return testEventTemplate.replace("${eventId}", innerEventId)
                .replace("${eventTimestamp}", innerEventTimestemp)
                .replace("${clientId}", clientId)
                .replace("${eventUUID}", eventUUID);
    }

    public static String getEventServiceStarted(String deplymentId) {
        String getServiceStartedTemplate = "{\n" +
                "\t\"payload\": {\n" +
                "\t\t\"name\": \"com.openmatics.cloud.core.service_multiclient-test-service\",\n" +
                "\t\t\"version\": \"latest\",\n" +
                "\t\t\"deploymentId\": \"${deploymentId}\",\n" +
                "\t\t\"requestedMsgDestinations\": [],\n" +
                "\t\t\"requestedEventDestinations\": {\n" +
                "\t\t\t\"inputs\": [{\n" +
                "\t\t\t\t\t\"id\": \"${eventFirst}\",\n" +
                "\t\t\t\t\t\"eventType\": {\n" +
                "\t\t\t\t\t\t\"name\": \"" + EVENT_EVENT_TYPE_NAME + "\",\n" +
                "\t\t\t\t\t\t\"version\": \"1.0.0\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"inputs\": null,\n" +
                "\t\t\t\t\t\"outputs\": null\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"id\": \"${eventSecond}\",\n" +
                "\t\t\t\t\t\"eventType\": {\n" +
                "\t\t\t\t\t\t\"name\": \"" + EVENT_EVENT_TYPE_NAME + "\",\n" +
                "\t\t\t\t\t\t\"version\": \"1.0.0\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"inputs\": null,\n" +
                "\t\t\t\t\t\"outputs\": null\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"id\": \"${topicFirst}\",\n" +
                "\t\t\t\t\t\"eventType\": {\n" +
                "\t\t\t\t\t\t\"name\": \"" + TOPIC_EVENT_TYPE_NAME + "\",\n" +
                "\t\t\t\t\t\t\"version\": \"1.0.0\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"inputs\": null,\n" +
                "\t\t\t\t\t\"outputs\": null\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"id\": \"${topicSecond}\",\n" +
                "\t\t\t\t\t\"eventType\": {\n" +
                "\t\t\t\t\t\t\"name\": \"" + TOPIC_EVENT_TYPE_NAME + "\",\n" +
                "\t\t\t\t\t\t\"version\": \"1.0.0\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"inputs\": null,\n" +
                "\t\t\t\t\t\"outputs\": null\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"outputs\": []\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"eventID\": \"6b9fd570-4ae3-4856-9997-fe2a70002a5c\",\n" +
                "\t\"eventTimestamp\": \"2018-09-25T11:50:12.651Z\",\n" +
                "\t\"sourceName\": \"com.openmatics.cloud.core.service_multiclient-test-service\"\n" +
                "}";
        return getServiceStartedTemplate
                .replace("${deploymentId}", deplymentId)
                .replace("${eventFirst}", EVENT_FIRST)
                .replace("${eventSecond}", EVENT_SECOND)
                .replace("${topicFirst}", TOPIC_FIRST)
                .replace("${topicSecond}", TOPIC_SECOND);


    }

}
