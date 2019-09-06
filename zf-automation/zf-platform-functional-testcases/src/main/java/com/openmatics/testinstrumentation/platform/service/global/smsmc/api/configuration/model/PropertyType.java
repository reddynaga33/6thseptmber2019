package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.configuration.model;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 02.04.2019.
 */
public enum PropertyType {
    CLIENT,
    @Deprecated
    SERVICE_DESCRIPTOR,
    CLIENT_USES_SERVICE,
    @Deprecated
    GLOBAL;
}
