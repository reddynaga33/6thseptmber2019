package com.openmatics.test.functional.platform.client.delete;

import org.testng.annotations.Factory;
import org.testng.annotations.Optional;

public class CleanClientsTestFactory {

    @Factory(enabled = true, dataProvider = "getClientsFromDbRemovedFromAd", dataProviderClass=CleanClientsReadFromDbDataProvider.class)
    public Object[] createInstances(String envKey, String clientId) throws Exception {
        //return new Object[]{};
        return new CleanClientsTest[]{ new CleanClientsTest(envKey, clientId, null)};
    }

}
