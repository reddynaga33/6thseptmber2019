package com.openmatics.testinstrumentation.platform.service.global.servicemanagement;

import com.google.common.base.Strings;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
//import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public class ServiceManagementServiceDeploymentsApi extends RestApiBase
    implements IServiceManagementServiceDeploymentsApi {

    public ServiceManagementServiceDeploymentsApi(IServiceConfiguration property) throws Exception {
        super("global", property);
    }

    @Override
    public HttpResponse getDeployments(String optional) throws Exception {
        String source = "deployments";
        if ( ! Strings.isNullOrEmpty(optional) ) source += "?" + optional;
        return super.getGetResponse(source);
    }

}
