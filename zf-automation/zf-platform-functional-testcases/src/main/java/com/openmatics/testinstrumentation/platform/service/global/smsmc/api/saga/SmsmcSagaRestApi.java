package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga;

import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.ISmsmcSagaRestApi;
import com.openmatics.testinstrumentation.platform.service.global.smsmc.api.saga.model.SagaType;
import com.openmatics.testinstrumentation.utils.exception.ObjectNotExistsException;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.apache.http.HttpStatus;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;


public class SmsmcSagaRestApi extends RestApiBase implements ISmsmcSagaRestApi {
    private static final String URL_TEMPLATE = "saga/%s/status/";

    public SmsmcSagaRestApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse get(String source) throws Exception {
        return super.getGetResponse(source);
    }

    @Override
    public String getStatus(String url) throws ObjectNotExistsException {
        try {
            HttpResponse response = super.getGetResponse(url);
            if (response.getStatus() == HttpStatus.SC_OK) {
                return response.getContentAsJson().getJSONObject("payload").getString("status");
            }
            throw new ObjectNotExistsException("The saga not found. Status: " + response.getStatus());
        } catch (IOException e) {
            System.err.println("The saga can't be load due to " + e.getMessage());
            throw new ObjectNotExistsException("The saga not found.", e);
        } catch (ServiceUnavailableException e) {
            System.err.println("The saga endpoint is unavailable due to " + e.getMessage());
            throw new ObjectNotExistsException("The saga not found.", e);
        }
    }

    @Override
    public String getStatus(String id, SagaType sagaType) throws ObjectNotExistsException {
        String url = String.format(URL_TEMPLATE + "%s", id, sagaType);
        String status = getStatus(url);
        return status;
    }
}
