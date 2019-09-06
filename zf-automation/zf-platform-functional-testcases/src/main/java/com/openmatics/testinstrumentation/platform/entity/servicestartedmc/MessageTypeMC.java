package com.openmatics.testinstrumentation.platform.entity.servicestartedmc;

import java.util.Objects;

/**
 * Created by Marek Rojik (marek.rojik@inventi.cz) on 08. 03. 2019
 */
public class MessageTypeMC {

    private String name;

    private String version;

    public MessageTypeMC() {
    }

    public MessageTypeMC(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public MessageTypeMC setName(String name) {
        this.name = name;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public MessageTypeMC setVersion(String version) {
        this.version = version;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageTypeMC that = (MessageTypeMC) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, version);
    }

    @Override
    public String toString() {
        return "MessageTypeMC{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
