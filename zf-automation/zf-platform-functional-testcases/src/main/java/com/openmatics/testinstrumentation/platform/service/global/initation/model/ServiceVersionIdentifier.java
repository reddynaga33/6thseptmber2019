package com.openmatics.testinstrumentation.platform.service.global.initation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 29.04.2019.
 */
public class ServiceVersionIdentifier {

    /**
     * Image name of given service version
     */
    private String image;

    /**
     * Version name of given service version
     */
    private String name;

    //@formatter:off
    public ServiceVersionIdentifier(
            @JsonProperty("image") String image,
            @JsonProperty("name") String name) {
        this.image = image;
        this.name = name;
    }
    //@formatter:on

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ServiceVersionIdentifier [image=" + image + ", name=" + name + "]";
    }
}
