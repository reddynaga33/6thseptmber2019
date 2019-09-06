package com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.com) on 27.03.2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CmsClientApplication {

    private boolean availableToOtherTenants;
    private List<String> clientIds;
    private String displayName;
    private List<String> identifierUris;
    private String groupMembershipClaims;
    @JsonIgnore
    private Object passwordCredentials;
    private String objectId;
    private String homepage;
    private String appId;
    private String errorUrl;

    public void setAvailableToOtherTenants(boolean availableToOtherTenants) {
        this.availableToOtherTenants = availableToOtherTenants;
    }

    public void setClientIds(List<String> clientIds) {
        this.clientIds = clientIds;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setIdentifierUris(List<String> identifierUris) {
        this.identifierUris = identifierUris;
    }

    public void setGroupMembershipClaims(String groupMembershipClaims) {
        this.groupMembershipClaims = groupMembershipClaims;
    }

    public void setPasswordCredentials(Object passwordCredentials) {
        this.passwordCredentials = passwordCredentials;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    public boolean isAvailableToOtherTenants() {
        return availableToOtherTenants;
    }

    public List<String> getClientIds() {
        return clientIds;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getIdentifierUris() {
        return identifierUris;
    }

    public String getGroupMembershipClaims() {
        return groupMembershipClaims;
    }

    public Object getPasswordCredentials() {
        return passwordCredentials;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getAppId() {
        return appId;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CmsClientApplication that = (CmsClientApplication) o;
        return isAvailableToOtherTenants() == that.isAvailableToOtherTenants() &&
                Objects.equals(getClientIds(), that.getClientIds()) &&
                Objects.equals(getDisplayName(), that.getDisplayName()) &&
                Objects.equals(getIdentifierUris(), that.getIdentifierUris()) &&
                Objects.equals(getGroupMembershipClaims(), that.getGroupMembershipClaims()) &&
                Objects.equals(getPasswordCredentials(), that.getPasswordCredentials()) &&
                Objects.equals(getObjectId(), that.getObjectId()) &&
                Objects.equals(getHomepage(), that.getHomepage()) &&
                Objects.equals(getAppId(), that.getAppId()) &&
                Objects.equals(getErrorUrl(), that.getErrorUrl());
    }

    @Override
    public int hashCode() {

        return Objects.hash(isAvailableToOtherTenants(), getClientIds(), getDisplayName(), getIdentifierUris(), getGroupMembershipClaims(), getPasswordCredentials(), getObjectId(), getHomepage(), getAppId(), getErrorUrl());
    }

    @Override
    public String toString() {
        return "CmsClientApplication{" +
                "availableToOtherTenants=" + availableToOtherTenants +
                ", clientIds=" + clientIds +
                ", displayName='" + displayName + '\'' +
                ", identifierUris=" + identifierUris +
                ", groupMembershipClaims='" + groupMembershipClaims + '\'' +
                ", passwordCredentials=" + passwordCredentials +
                ", objectId='" + objectId + '\'' +
                ", homepage='" + homepage + '\'' +
                ", appId='" + appId + '\'' +
                ", errorUrl='" + errorUrl + '\'' +
                '}';
    }
}
