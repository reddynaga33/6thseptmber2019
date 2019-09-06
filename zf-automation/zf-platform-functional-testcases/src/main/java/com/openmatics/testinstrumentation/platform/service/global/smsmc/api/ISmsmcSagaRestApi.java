package com.openmatics.testinstrumentation.platform.service.global.smsmc.api;

import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.model.SagaType;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface ISmsmcSagaRestApi {

    @Deprecated
    HttpResponse get(String source) throws Exception;

    /**
     * Get saga status.
     *
     * @param sagaId saga id
     * @return saga status
     */
    String getStatus(String sagaId) throws ObjectNotExistsException;

    /**
     * Get saga status by saga id and
     *
     * @param id       saga id
     * @param sagaType saga type
     * @return saga status
     * @throws ObjectNotExistsException the saga not found.
     */
    String getStatus(String id, SagaType sagaType) throws ObjectNotExistsException;
}
