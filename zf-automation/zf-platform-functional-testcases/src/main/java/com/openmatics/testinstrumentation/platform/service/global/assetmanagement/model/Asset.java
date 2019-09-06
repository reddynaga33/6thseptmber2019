package com.openmatics.testinstrumentation.platform.service.global.assetmanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.minidev.json.JSONObject;

import java.time.Instant;
import java.util.List;

/**
 * Created by Zdeněk Záhlava (zdenek.zahlava@zf.com) on 29.03.2019
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Asset {

    private String id;
    private String name;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant creationTime;
    private JSONObject assetType;
    private List<String> clientIdList;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public void setAssetType(JSONObject assetType) {
        this.assetType = assetType;
    }

    public void setClientIdList(List<String> clientIdList) {
        this.clientIdList = clientIdList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public JSONObject getAssetType() {
        return assetType;
    }

    public List<String> getClientIdList() {
        return clientIdList;
    }

}
