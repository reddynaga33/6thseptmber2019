package com.openmatics.testinstrumentation.platform.service.global.clientmanagement;

import com.google.common.base.Strings;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model.CmsClient;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model.CmsClientTemplate;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.ResourceLoader;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.json.JSONObject;
import org.json.JSONArray;

public class ClientConfiguration {
    private static final String DEFAULT_CLIENT_RESOURCE_TEMPLATE = "platform/client/AutomatedTest00.%s.json";
    public static final String DEFAULT_USER_BASE_NAME = "AutoTest";
    private IEnvironmentConfiguration envConf;
    private String clientSuffix = null;
    private CmsClientTemplate template = null;
    private CmsClient client = null;
    private String clientId = null;


    public ClientConfiguration(IEnvironmentConfiguration envConf) {
        this.envConf = envConf;
    }

    public ClientConfiguration(IEnvironmentConfiguration envConf, String clientId) {
        this.clientId = clientId;
        this.envConf = envConf;
    }

    public ClientConfiguration(IEnvironmentConfiguration envConf, CmsClientTemplate template) {
        this.template = template;
        this.clientSuffix = template.getSuffix();
        this.envConf = envConf;
    }


    public String getInputClientId() throws Exception {
        return clientId;
    }

    public String getClientSuffix() {
        return clientSuffix;
    }

    public CmsClient getClient() throws Exception {
        if (client == null) {
            if (!Strings.isNullOrEmpty(clientId)) {
                loadClientById(clientId);
            } else if (template != null) {
                loadClientByTemplate(template);
            } else {
                throw new RuntimeException("CmsClient suffix or id don´t been provided therefore con not possible load the client");
            }
        }
        return client;
    }


    public CmsClientTemplate getTemplate() throws Exception {
        if (template == null) template = new CmsClientTemplate(getClientSuffix());
        return template;
    }


    public void setClient(JSONObject client) throws Exception {
        this.client = JsonUtils.fromJson(client.toString(), CmsClient.class);
    }


    private void loadClientByTemplate(CmsClientTemplate template) throws Exception {
        loadClientByName(template.getValue().getString("name"));
    }

    private void loadClientByName(String name) throws Exception {
        client = null;
        ClientManagementApi api = new ClientManagementApi(envConf.getEnvKey());
        HttpResponse response = api.getClientApi().getClients();
        for (Object item : response.getContentAsJsonArray()) {
            JSONObject restClient = (JSONObject) item;
            if (restClient.getString("name").equals(name)) {
                setClient(restClient);
            }
        }
    }

    private void loadClientById(String clientId) throws Exception {
        client = null;
        ClientManagementApi api = new ClientManagementApi(envConf.getEnvKey());
        HttpResponse response = api.getClientApi().getClientById(clientId);
        setClient(response.getContentAsJson());
    }


    public JSONArray getUsersTemplates() throws Exception {
        return ResourceLoader.loadAsJsonArray(resolveResourcePath("users"));
    }


    public String getResourcePath(String entityName) {
        return resolveResourcePath(entityName);
    }


    public static String resolveResourcePath(String entityName) {
        return String.format(DEFAULT_CLIENT_RESOURCE_TEMPLATE, entityName);
    }

}
