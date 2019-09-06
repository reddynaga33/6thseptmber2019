package com.openmatics.testinstrumentation.platform.service.global.servicemanagement;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IServiceManagementServiceDeploymentsApi {

    HttpResponse getDeployments(String optional) throws Exception;

}
