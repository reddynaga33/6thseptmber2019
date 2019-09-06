package com.openmatics.testinstrumentation.platform.cases.servicemanagement;

public class DestinationContext
{
    public String id = null;
    public String destinationType  = null;
    public String name  = null;
    public String eventTypeId  = null;
    public Boolean isSynthetic = false;

    public static String getSqlQuery(String deploymentId){
        return String.format(sqlQueryInputDestinationTemplate, deploymentId);
    }

    public static String getSqlIsSubscription(String topicName, String deploymentId){
        return String.format(sqlQueryIsSubscriptionTemplate, deploymentId, topicName);
    }

    public static String sqlQueryInputDestinationTemplate = "select \n" +
            "[event_destination].* \n" +
            "from [service_management_service_schema].[deployment_to_ev_dest]\n" +
            "join [service_management_service_schema].[event_destination]\n" +
            "on [deployment_to_ev_dest].[event_destination_id]=[event_destination].[id]\n" +
            "where [type]='INPUT'\n" +
            "and [deployment_id]='%s'";

    public static String sqlQueryIsSubscriptionTemplate = "select \n" +
            "count(*) as number\n" +
            "from [service_management_service_schema].[deployment_to_ev_dest]\n" +
            "join [service_management_service_schema].[event_destination]\n" +
            "on [deployment_to_ev_dest].[event_destination_id]=[event_destination].[id]\n" +
            "where [type]='INPUT'\n" +
            "and [deployment_id]='%s'\n" +
            "and [event_destination].name_identifier='%s'";




}
