package com.openmatics.test.functional.platform.client.delete;

import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.ClientManagementApi;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.PermanentClientIdList;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.db.ClientCacheRepository;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model.CmsClient;
import com.openmatics.testinstrumentation.utils.JsonUtils;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import com.openmatics.testinstrumentation.utils.sql.DbConnectionFactory;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


public class CleanClientsReadFromDbDataProvider {

    private PermanentClientIdList permanentClientIds;
    private String envKey = null;
    private Map<String, CmsClient> cmsClients = null;

    public CleanClientsReadFromDbDataProvider() {
        this.permanentClientIds = new PermanentClientIdList();
    }

    @DataProvider(name = "getClientsFromDbRemovedFromAd")
    public Object[][] getClientsFromDbRemovedFromAd(ITestContext testContext) throws Exception {
        envKey = testContext.getCurrentXmlTest().getParameter("envKey");
        List<String> clientIds = loadIdsFromDb();
        guard(clientIds);

        List<String> clientFiltered = clientIds.stream()
                .filter(clientId -> !isInAd(clientId))
                .collect(Collectors.toList());

        return covertListToData(clientFiltered);
    }

    @DataProvider(name = "getClientsFromDbYoungerAgeInDays")
    public Object[][] getClientsFromDbByAgeInDays(ITestContext testContext) throws Exception {
        envKey = testContext.getCurrentXmlTest().getParameter("envKey");
        int ageInDays = Integer.parseInt(testContext.getCurrentXmlTest().getParameter("ageInDays"));

        List<String> clientIds = loadIdsFromDb();

        guard(clientIds);

        List<String> clientFiltered = clientIds.stream()
                .filter(clientId -> !isOlderThan(clientId, ageInDays))
                .filter(clientId -> isInAd(clientId))
                .collect(Collectors.toList());

        return covertListToData(clientFiltered);
    }

    // is in ActiveDirectory
    @DataProvider(name = "getClientsFromDbBetweenDate")
    public Object[][] getClientsFromDbBetweenDate(ITestContext testContext) throws Exception {
        envKey = testContext.getCurrentXmlTest().getParameter("envKey");
        String startDate = testContext.getCurrentXmlTest().getParameter("startDate");
        String endDate = testContext.getCurrentXmlTest().getParameter("endDate");

        List<String> clientIds = loadIdsFromDb();

        guard(clientIds);

        List<String> clientFiltered = clientIds.stream()
                .filter(clientId -> isBetweenDate(clientId, Instant.parse(startDate), Instant.parse(endDate)))
                .filter(clientId -> isInAd(clientId))
                .collect(Collectors.toList());

        return covertListToData(clientFiltered);
    }

    private Object[][] covertListToData(List<String> clientIds) {
        Object[][] data = new Object[clientIds.size()][2];
        System.out.println("Data provider prepared this data:");
        for (int i = 0; i < clientIds.size(); i++) {
            data[i][0] = envKey;
            data[i][1] = clientIds.get(i);
            System.out.println("\t" + data[i][0] + "\t" + data[i][1]);
        }
        System.out.println(String.format("END of data with size %s.\n", clientIds.size()));
        return data;
    }

    private void guard(List<String> clientIds) {
        // Guard ! Check if not exists client which is permanent list
        for (String permanentClient : permanentClientIds.get(envKey)) {
            if (clientIds.contains(permanentClient)) {
                throw new RuntimeException("Permanent client is in to the collection for deleting");
            }
        }
    }

    private List<String> loadIdsFromDb() throws Exception {
        List<String> envPermanentClientIds = permanentClientIds.get(envKey);
        IEnvironmentConfiguration envConf = new EnvironmentConfiguration(envKey);
        List<String> clientIds = null;
        try (DbConnectionFactory connectionFactory = new DbConnectionFactory(envConf.getDbConnectionString())) {
            ClientCacheRepository repository = new ClientCacheRepository(connectionFactory);
            clientIds = repository.getAllWithExcludesList(envPermanentClientIds);
        }
        return clientIds;
    }

    private Map<String, CmsClient> loadClientsFormAd() throws Exception {
        HashMap<String, CmsClient> result = new HashMap<String, CmsClient>();
        ClientManagementApi smcApi = new ClientManagementApi(envKey);
        HttpResponse response = smcApi.getClientApi().getClients();
        if (response.getStatus() != 200) throw new RuntimeException("Bad response of sms get clients !");
        for (Object object : response.getContentAsJsonArray()) {
            CmsClient client = JsonUtils.fromJson(object.toString(), CmsClient.class);
            result.put(client.getId(), client);
        }
        return result;
    }

    private CmsClient getCmsClients(String clientId) throws Exception {
        if (cmsClients == null) {
            cmsClients = loadClientsFormAd();
        }
        return cmsClients.get(clientId);
    }

    private boolean isInAd(String clientId) {
        try {
            if (getCmsClients(clientId) == null) return false;
            return true;

        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }


    private boolean isOlderThan(String clientId, int numberOfDays) {
        try {
            CmsClient client = getCmsClients(clientId);
            if (client == null) return false;
            Boolean isOlder = client.getCreationTime().plus(numberOfDays, ChronoUnit.DAYS).isBefore(Instant.now());
            //System.out.println("isOlder: " + client.getCreationTime() + " - " + isOlder);
            return isOlder;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    private boolean isBetweenDate(String clientId, Instant start, Instant end) {
        try {
            CmsClient client = getCmsClients(clientId);
            if (client == null) return false;
            Boolean isBetween = client.getCreationTime().isAfter(start) && client.getCreationTime().isBefore(end);
            //System.out.println("isBetween : " + client.getCreationTime() + " - " + isBetween);
            return isBetween;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }
}
