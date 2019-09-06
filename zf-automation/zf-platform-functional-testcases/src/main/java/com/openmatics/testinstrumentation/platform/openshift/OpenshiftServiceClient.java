package com.openmatics.testinstrumentation.platform.openshift;

import com.openmatics.testinstrumentation.platform.utils.OpenshiftClient;
import com.openshift.restclient.model.IService;

import java.util.List;
import java.util.stream.Collectors;

public class OpenshiftServiceClient {

    private OpenshiftResourceClient loader;

    public OpenshiftServiceClient(OpenshiftClient client) {
        loader = new OpenshiftResourceClient(client);
    }

    public List<IService> getAllService(){
       return loader.getAllServices()
               .stream()
               .map(res -> (IService) res)
               .collect(Collectors.toList());
    }

    public List<IService> getServiceBySubstringInName(String substring){
        return loader.filterBySubstringInName(loader.getAllServices(), substring)
                .stream()
                .map(res -> (IService) res)
                .collect(Collectors.toList());
    }

}
