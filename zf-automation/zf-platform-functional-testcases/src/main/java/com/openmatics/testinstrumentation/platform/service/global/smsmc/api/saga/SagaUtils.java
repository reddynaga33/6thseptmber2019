package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.HttpStatus;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 01.04.2019.
 */
public class SagaUtils {

    private SagaUtils() {
    }

    public static String getSagaStatus(HttpResponse httpResponse) {
        if (httpResponse.getStatus() == HttpStatus.SC_ACCEPTED) {
            return httpResponse.getContentAsJson().getString("status");
        }
        return null;
    }
}
