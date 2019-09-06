package com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model;

import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientConfiguration;
import com.openmatics.testinstrumentation.utils.ResourceLoader;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CmsClientTemplate {

    private String suffix;
    private JSONObject template = null;

    public CmsClientTemplate(String suffix) throws Exception {
        this.suffix = suffix;
        loadClientTemplate(suffix);
    }

    public JSONObject getValue() {
        return template;
    }

    public String getSuffix() {
        return suffix;
    }

    private void loadClientTemplate(String suffix) throws Exception {
        if (suffix == null) throw new RuntimeException("CmsClient suffix did not set. CmsClient for load is unknown.");
        Map<String, String> prop = new HashMap<>();
        prop.put("clientSuffix", suffix);
        String path = ClientConfiguration.resolveResourcePath("client");
        template = ResourceLoader.LoadAsJson(path, prop);
    }

}
