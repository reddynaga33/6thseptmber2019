package com.openmatics.test.functional;

import com.google.common.base.Strings;
import com.openmatics.testinstrumentation.automotive.AutomotiveInstrumentation;
import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.PlatformInstrumentation;
import com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model.CmsClientTemplate;
import com.openmatics.testinstrumentation.utils.selenium.ISeleniumService;
import com.openmatics.testinstrumentation.utils.selenium.SeleniumChromeService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class ApiTestBase {

    private static Map<String, PlatformInstrumentation> platformInstrumentationsList = new HashMap<>();
    private AutomotiveInstrumentation automotiveInstrumentation = null;
    private ISeleniumService seleniumService = null;
    protected PlatformInstrumentation platformInstrumentation = null;
    protected String envKey;

    public ApiTestBase() {
    }

    public ApiTestBase(String envKey, String clientId, String clientSuffix) throws Exception {
        init(envKey, clientId, clientSuffix);
    }

    private void init(String envKey, String clientId, String clientSuffix) throws Exception {
        if (Strings.isNullOrEmpty(envKey)) throw new RuntimeException("Environment key is required.");
        if (!Strings.isNullOrEmpty(clientId)) {
            platformInstrumentation = getPlatformInstrumentation(envKey, clientId);
        } else if (!Strings.isNullOrEmpty(clientSuffix)) {
            CmsClientTemplate clientTemplate = new CmsClientTemplate(clientSuffix);
            platformInstrumentation = getPlatformInstrumentation(envKey, clientTemplate);
        } else {
            throw new RuntimeException("CmsClient suffix or client id is required.");
        }
        this.envKey = envKey;
        System.out.println("Test was init with this base parameters:");
        System.out.println(String.format("Environment key is: %s", envKey));
        System.out.println(String.format("CmsClient id is: %s", clientId));
        System.out.println(String.format("CmsClient suffix is: %s", clientSuffix));
    }

    @BeforeClass()
    @Parameters({"envKey", "clientId", "clientSuffix"})
    public void beforeClassTestBase(@Optional("") String envKey, @Optional("") String clientId, @Optional("") String clientSuffix) throws Exception {
        // If platformInstrumentation is not null that class was initiated by constructor by factory class
        if (platformInstrumentation == null) {
            init(envKey, clientId, clientSuffix);
        }
        executeBeforeClass();
    }

    private String getPlatformInstrumentationKey(String envKey, String key) {
        return String.format("%s/%s", envKey, key);
    }

    private PlatformInstrumentation getPlatformInstrumentation(String envKey, CmsClientTemplate template) throws Exception {
        String dictionaryKey = getPlatformInstrumentationKey(envKey, template.getSuffix());
        if (platformInstrumentationsList.containsKey(dictionaryKey)) {
            return platformInstrumentationsList.get(dictionaryKey);
        }
        PlatformInstrumentation instrumentation = new PlatformInstrumentation(new EnvironmentConfiguration(envKey), template);
        platformInstrumentationsList.putIfAbsent(dictionaryKey, instrumentation);
        return instrumentation;
    }

    private PlatformInstrumentation getPlatformInstrumentation(String envKey, String key) throws Exception {
        String dictionaryKey = getPlatformInstrumentationKey(envKey, key);
        if (platformInstrumentationsList.containsKey(dictionaryKey)) {
            return platformInstrumentationsList.get(dictionaryKey);
        }
        PlatformInstrumentation instrumentation = new PlatformInstrumentation(new EnvironmentConfiguration(envKey), key);
        platformInstrumentationsList.putIfAbsent(dictionaryKey, instrumentation);
        return instrumentation;
    }

    protected void executeBeforeClass() throws Exception {
    }

    protected void executeAfterClass() throws Exception {
    }


    @AfterClass()
    public void afterClassTestBase() throws Exception {
        if (seleniumService != null) seleniumService.Close();
        executeAfterClass();
    }


    protected AutomotiveInstrumentation getAutomotiveInstr() {
        if (automotiveInstrumentation == null) {
            automotiveInstrumentation = new AutomotiveInstrumentation(platformInstrumentation.getEnvConf(),
                    platformInstrumentation.getClientConf());
        }
        return automotiveInstrumentation;
    }


    protected ISeleniumService getSeleniumService() throws Exception {
        if (seleniumService == null) {

            String remoteUrl = System.getProperty("remoteUrl");
            String webDriverPath = System.getProperty("webDriverPath");
            System.out.println("Property remoteUrl: " + remoteUrl);
            System.out.println("Property webDriverPath: " + webDriverPath);

            if (org.testng.util.Strings.isNullOrEmpty(remoteUrl) != true) {
                seleniumService = SeleniumChromeService.getSeleniumChromeServiceWithAvailableRemoteDriver(remoteUrl, 600);
            } else if (org.testng.util.Strings.isNullOrEmpty(webDriverPath) != true) {
                seleniumService = new SeleniumChromeService(new File(webDriverPath));
            } else {
                throw new RuntimeException("Uri of web driver or selenium server not present");
            }
        }
        return seleniumService;
    }


}
