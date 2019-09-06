package com.openmatics.testinstrumentation.service.smsmc;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcSagaRestApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.model.SagaType;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;

import java.util.concurrent.TimeUnit;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 01.04.2019.
 */
public class SagaService {
    private final int WAIT_SLEEP_TIME_S = 5;
    private final String SUCCESS_STATUS = "SUCCESS";
    private ISmsmcSagaRestApi sagaRestApi;

    public SagaService(ISmsmcSagaRestApi sagaRestApi) {
        this.sagaRestApi = sagaRestApi;
    }

    public boolean isSagaStatusSuccess(String url, int requestCount) {
        boolean sagaCheck = false;
        for (int count = 0; count < requestCount; count++) {
            try {
                String status = sagaRestApi.getStatus(url);
                sagaCheck = status != null && SUCCESS_STATUS.equalsIgnoreCase(status);
                if (sagaCheck) {
                    break;
                }
            } catch (ObjectNotExistsException e) {
                sleep();
            }
        }
        return sagaCheck;
    }

    public boolean isSagaStatusSuccess(String id, SagaType sagaType, int requestCount) {
        boolean sagaCheck = false;
        for (int count = 0; count < requestCount; count++) {
            try {
                String status = sagaRestApi.getStatus(id, sagaType);
                sagaCheck = status != null && SUCCESS_STATUS.equalsIgnoreCase(status);
                if (sagaCheck) {
                    break;
                }
            } catch (ObjectNotExistsException e) {
                sleep();
            }
        }
        return sagaCheck;
    }

    private synchronized void sleep() {
        try {
            TimeUnit.SECONDS.timedWait(this, WAIT_SLEEP_TIME_S);
        } catch (InterruptedException e) {
            System.err.println("The thread is interrupted during the timeout: " + e.getMessage());
        }
    }
}
