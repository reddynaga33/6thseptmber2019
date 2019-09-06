package com.openmatics.testinstrumentation.platform.service.global.initation;

import com.openmatics.testinstrumentation.utils.net.HttpResponse;

public interface IInitiationServiceVehicleApi {

    HttpResponse createVehicle(String content) throws Exception;

    HttpResponse updateVehicle(String id, String content) throws Exception;

    HttpResponse deleteVehicle(String id) throws Exception;

}
