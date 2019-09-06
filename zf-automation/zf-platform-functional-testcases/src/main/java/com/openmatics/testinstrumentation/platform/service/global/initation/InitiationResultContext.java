package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.JSONObject;

import java.util.concurrent.TimeoutException;

public class InitiationResultContext {

    public static String OPERATION_DELETE_STATUS = "DELETE";
    public static String OPERATION_CREATE_STATUS = "CREATE";

    public static String getOperationStatus(JSONObject result, int index) {
        return result.getJSONArray("results").getJSONObject(index).getString("operation");
    }

    public static String getOperationStatus(JSONObject result) {
        return getOperationStatus(result, 0);
    }

    public static JSONObject wait4result(IInitiationServiceRequestApi api, String requestId) throws Exception {

        return  wait4result(api, requestId, 1, 3000, 10);
    }

    public static JSONObject wait4result(IInitiationServiceRequestApi api,
                                         String requestId,
                                         int resultCount) throws Exception {

        return  wait4result(api, requestId, resultCount, 3000, 10);
    }

    public static JSONObject wait4result(IInitiationServiceRequestApi api,
                                         String requestId,
                                         int resultCount,
                                         int timeoutStep,
                                         int timeoutStepsCount) throws Exception {

        int count = 0;
        HttpResponse response;
        JSONObject result = null;
        do {
            if (count == timeoutStepsCount ) throw new TimeoutException("Waiting result is over timeout");
            response = api.GetResult(requestId);
            result = response.getContentAsJson();
            System.out.println("Wait fro result step time (ms): " + (timeoutStep * count) + String.format(" %s/%s ", count, timeoutStepsCount));
            Thread.sleep(timeoutStep);
            count ++;
        } while (result.getJSONArray("results").length() != resultCount);
        return result;
    }

}
