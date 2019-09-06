package com.openmatics.testinstrumentation.platform.utils;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 01.03.2019.
 */
public final class DestinationName {

    public static final String SMS_MC_CLIENT_USES_SERVICE = "com.openmatics.cloud.core.service_service-management-service-multiclient_clientusesservice";
    public static final String SMS_MC_SERVICE_STARTED = "com.openmatics.cloud.core.service_service-management-service-multiclient_servicestartedmc";
    public static final String SMS_MC_SERVICE_DESCRIPTOR_CHANGE = "com.openmatics.cloud.core.service_service-management-service-multiclient_servicedescriptor";
    public static final String SMS_MC_DESTINATION_CHANGE = "com.openmatics.cloud.core.service_service-management-service-multiclient_destinationchanged";
    public static final String SMS_MC_CONFIGURATION = "com.openmatics.cloud.core.service_service-management-service-multiclient_serviceconfiguration";
    public static final String SMS_MC_CONFIGURATION_CHANGED = "com.openmatics.cloud.core.service_service-management-service-multiclient_serviceconfigurationchanged";
    public static final String SMS_SERVICE_STARTED = "com.openmatics.cloud.core.service_service-management-service_servicestarted";

    private DestinationName() {
    }
}
