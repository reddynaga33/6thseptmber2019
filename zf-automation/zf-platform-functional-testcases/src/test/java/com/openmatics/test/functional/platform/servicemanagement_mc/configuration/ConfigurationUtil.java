package com.openmatics.test.functional.platform.servicemanagement_mc.configuration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 28.03.2019.
 */
public final class ConfigurationUtil {
    public final static String ARTIFACT_ID = "configuration-test";
    public final static String GROUP_ID = "com.openmatics.cloud.test.service";

    private ConfigurationUtil() {
    }

    public static Map<String, String> getDefaultConfigurationMap(String prefix) {
        return Stream.of("conf1", "conf2", "conf3", "conf4", "conf5", "conf6")
                .map(s -> prefix + s)
                .collect(Collectors.toMap(o -> o + "key", o -> o + "value"));
    }
}
