package com.openmatics.testinstrumentation.platform.service.global.clientmanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.com) on 27.03.2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CmsClient {

    private String id;
    private String parentId;
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant creationTime;
    private boolean enabled;
    private CmsClientAddress address;
    private String phone;
    private String fax;
    private String web;
    private String email;
    private String logoUrl;
    private String owiPassword;
    private String owiUserName;
    private String owiUrl;
    @JsonIgnore
    private List<CmsClientUser> users;
    @JsonIgnore
    private List<CmsClientUserGroup> userGroups;
    private List<CmsClientApplication> applications;
    private CmsClientConfiguration configuration;

    public void setId(String id) {
        this.id = id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAddress(CmsClientAddress address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setUsers(List<CmsClientUser> users) {
        this.users = users;
    }

    public void setUserGroups(List<CmsClientUserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public void setApplications(List<CmsClientApplication> applications) {
        this.applications = applications;
    }

    public void setConfiguration(CmsClientConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setOwiPassword(String owiPassword) {
        this.owiPassword = owiPassword;
    }

    public void setOwiUserName(String owiUserName) {
        this.owiUserName = owiUserName;
    }

    public void setOwiUrl(String owiUrl) {
        this.owiUrl = owiUrl;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public CmsClientAddress getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getWeb() {
        return web;
    }

    public String getEmail() {
        return email;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public List<CmsClientUser> getUsers() {
        return users;
    }

    public List<CmsClientUserGroup> getUserGroups() {
        return userGroups;
    }

    public List<CmsClientApplication> getApplications() {
        return applications;
    }

    public CmsClientConfiguration getConfiguration() {
        return configuration;
    }

    public String getOwiPassword() {
        return owiPassword;
    }

    public String getOwiUserName() {
        return owiUserName;
    }

    public String getOwiUrl() {
        return owiUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CmsClient client = (CmsClient) o;
        return isEnabled() == client.isEnabled() &&
                Objects.equals(getId(), client.getId()) &&
                Objects.equals(getParentId(), client.getParentId()) &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getCreationTime(), client.getCreationTime()) &&
                Objects.equals(getAddress(), client.getAddress()) &&
                Objects.equals(getPhone(), client.getPhone()) &&
                Objects.equals(getFax(), client.getFax()) &&
                Objects.equals(getWeb(), client.getWeb()) &&
                Objects.equals(getEmail(), client.getEmail()) &&
                Objects.equals(getLogoUrl(), client.getLogoUrl()) &&
                Objects.equals(getOwiPassword(), client.getOwiPassword()) &&
                Objects.equals(getOwiUserName(), client.getOwiUserName()) &&
                Objects.equals(getOwiUrl(), client.getOwiUrl()) &&
                Objects.equals(getUsers(), client.getUsers()) &&
                Objects.equals(getUserGroups(), client.getUserGroups()) &&
                Objects.equals(getApplications(), client.getApplications()) &&
                Objects.equals(getConfiguration(), client.getConfiguration());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getParentId(), getName(), getCreationTime(), isEnabled(), getAddress(), getPhone(), getFax(), getWeb(), getEmail(), getLogoUrl(), getOwiPassword(), getOwiUserName(), getOwiUrl(), getUsers(), getUserGroups(), getApplications(), getConfiguration());
    }

    @Override
    public String toString() {
        return "CmsClient{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                ", enabled=" + enabled +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", web='" + web + '\'' +
                ", email='" + email + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", owiPassword='" + owiPassword + '\'' +
                ", owiUserName='" + owiUserName + '\'' +
                ", owiUrl='" + owiUrl + '\'' +
                ", users=" + users +
                ", userGroups=" + userGroups +
                ", applications=" + applications +
                ", configuration=" + configuration +
                '}';
    }

}
