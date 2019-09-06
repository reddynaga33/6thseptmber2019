package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model;

import java.util.Objects;

/**
 * Class will be deleted after Message dispatcher wouldn't need to know message type of queue destination
 * Created by Marek Rojik (marek.rojik@inventi.cz) on 11. 03. 2019
 */
@Deprecated
public class MessageType {

    private String id;

    private String name;

    private String version;

    public MessageType() {
    }

    public MessageType(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public MessageType setName(String name) {
        this.name = name;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public MessageType setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getId() {
        return id;
    }

    public MessageType setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "MessageType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageType that = (MessageType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, version);
    }
}
