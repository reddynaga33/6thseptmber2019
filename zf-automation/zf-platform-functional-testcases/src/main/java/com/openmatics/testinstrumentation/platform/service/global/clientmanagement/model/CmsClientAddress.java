package com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.com) on 27.03.2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CmsClientAddress {
    private String street = null;
    private String streetNumber = null;
    private String furtherInfo = null;
    private String city = null;
    private String state = null;
    private String zip = null;
    private String country = null;

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getFurtherInfo() {
        return furtherInfo;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setFurtherInfo(String furtherInfo) {
        this.furtherInfo = furtherInfo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
