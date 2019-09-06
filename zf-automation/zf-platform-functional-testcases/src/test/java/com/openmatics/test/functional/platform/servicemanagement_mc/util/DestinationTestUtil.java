package com.openmatics.test.functional.platform.servicemanagement_mc.util;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Destination;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.DestinationType;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.Direction;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model.MessageType;
import org.apache.commons.lang3.BooleanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 01.03.2019.
 */
public class DestinationTestUtil {
    public static final String SEPERATOR = "_";
    public final static String ADDED = "ADD";
    public final static String CREATED = "CREATED";
    public final static String DELETED = "DELETED";

    public final static String MESSAGE_TYPE_NAME = "testing-message-type";
    public final static String MESSAGE_TYPE_VERSION = "1.0.0";

    public final static String ARTIFACT_ID = "service-started-test";
    public final static String GROUP_ID = "com.openmatics.cloud.test.service";

    public final static String QUEUE_NAME_1 = "com.openmatics.cloud.test.first-test-queue";
    public final static String QUEUE_NAME_2 = "com.openmatics.cloud.test.second-test-queue";
    public final static String QUEUE_NAME_3 = "com.openmatics.cloud.test.third-test-queue";
    public final static String QUEUE_NAME_4 = "com.openmatics.cloud.test.fourth-test-queue";
    public final static String QUEUE_NAME_5 = "com.openmatics.cloud.test.fifth-test-queue";

    public final static String TOPIC_NAME_1 = "com.openmatics.cloud.test.first-test-topic";
    public final static String TOPIC_NAME_2 = "com.openmatics.cloud.test.second-test-topic";
    public final static String TOPIC_NAME_3 = "com.openmatics.cloud.test.third-test-topic";
    public final static String TOPIC_NAME_4 = "com.openmatics.cloud.test.fourth-test-topic";
    public final static String TOPIC_NAME_5 = "com.openmatics.cloud.test.fifth-test-topic";
    public final static String TOPIC_NAME_6 = "com.openmatics.cloud.test.sixth-test-topic";
    public final static String TOPIC_NAME_7 = "com.openmatics.cloud.test.seventh-test-topic";

    public static List<Destination> getDefaultDestinations() {
        List<Destination> destinations = new ArrayList<>();
        destinations.add(createQueue(QUEUE_NAME_1, Direction.IN, false, false));
        destinations.add(createQueue(QUEUE_NAME_2, Direction.IN, true, false));
        destinations.add(createQueue(QUEUE_NAME_3, Direction.IN, false, true));

        destinations.add(createDestination(TOPIC_NAME_1, DestinationType.TOPIC, Direction.IN, false, false));
        destinations.add(createDestination(TOPIC_NAME_2, DestinationType.TOPIC, Direction.OUT, false, false));
        destinations.add(createDestination(TOPIC_NAME_3, DestinationType.TOPIC, Direction.IN, true, false));
        destinations.add(createDestination(TOPIC_NAME_4, DestinationType.TOPIC, Direction.OUT, true, false));
        destinations.add(createDestination(TOPIC_NAME_5, DestinationType.TOPIC, Direction.IN, false, true));
        return destinations;
    }

    public static Map<String, List<Destination>> getDefaultDestinationsRemoved() {
        Map<String, List<Destination>> map = new HashMap<>();

        List<Destination> defaultDestinations = getDefaultDestinations();
        List<Destination> deleted = Arrays.asList(
                defaultDestinations.stream().filter(destination -> destination.getType() == DestinationType.QUEUE && BooleanUtils.isNotTrue(destination.getSingleClient())).findAny().orElse(null),
                defaultDestinations.stream().filter(destination -> destination.getType() == DestinationType.QUEUE && BooleanUtils.isTrue(destination.getSingleClient())).findAny().orElse(null),
                defaultDestinations.stream().filter(destination -> destination.getType() == DestinationType.TOPIC && BooleanUtils.isNotTrue(destination.getSingleClient())).findAny().orElse(null),
                defaultDestinations.stream().filter(destination -> destination.getType() == DestinationType.TOPIC && BooleanUtils.isTrue(destination.getSingleClient())).findAny().orElse(null)
        );
        defaultDestinations.removeAll(deleted);
        List<Destination> add = Arrays.asList(
                createQueue(QUEUE_NAME_4, Direction.IN, false, true),
                createQueue(QUEUE_NAME_5, Direction.IN, true, true),
                createDestination(TOPIC_NAME_6, DestinationType.TOPIC, Direction.IN, false, true),
                createDestination(TOPIC_NAME_7, DestinationType.TOPIC, Direction.IN, true, true)
        );

        defaultDestinations.addAll(add);
        map.put(CREATED, defaultDestinations);
        map.put(DELETED, deleted);
        map.put(ADDED, add);
        return map;
    }

    public static List<Destination> getMtDestination(List<Destination> destinations) {
        return destinations.stream().filter(destination -> BooleanUtils.isNotTrue(destination.getSingleClient())).collect(Collectors.toList());
    }

    public static List<Destination> getStDestination(List<Destination> destinations, String clientId) {
        List<Destination> collect = destinations.stream()
                .filter(destination -> BooleanUtils.isTrue(destination.getSingleClient()))
                .map(destination -> destination.setNameIdentifier(destination.getNameIdentifier() + SEPERATOR + clientId))
                .collect(Collectors.toList());
        return collect;
    }

    public static Destination createQueue(String name, Direction direction, boolean singleClient, boolean partitioning) {
        Destination destination = createDestination(name, DestinationType.QUEUE, direction, singleClient, partitioning);
        destination.setMessageType(new MessageType(MESSAGE_TYPE_NAME, MESSAGE_TYPE_VERSION));
        return destination;
    }

    public static Destination createDestination(String name, DestinationType destinationType, Direction direction, boolean singleClient, boolean partitioning) {
        Destination destination = new Destination();
        destination.setNameIdentifier(name);
        destination.setDirection(direction);
        destination.setPartitioning(partitioning);
        destination.setType(destinationType);
        destination.setSingleClient(singleClient);
        return destination;
    }

}
