package com.openmatics.testinstrumentation.platform.service.global.smsmc.api.destination.model;


import com.openmatics.testinstrumentation.platform.entity.wrapper.Operation;
import com.openmatics.testinstrumentation.platform.service.global.servicemanagement.db.model.ServiceDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Destination changed event entity. Should inform a service about its destination being created/deleted, especially
 * on the Service Started event. It contains batch changes, ie. all in one event.
 */
public class DestinationChanged {

    private ServiceDescriptor serviceDescriptor;
    private Map<Operation, List<Destination>> changes = new HashMap<>();

    public DestinationChanged() {
    }

    public DestinationChanged(ServiceDescriptor serviceDescriptor, Map<Operation, List<Destination>> changes) {
        this.serviceDescriptor = serviceDescriptor;
        this.changes = changes;
    }

    public ServiceDescriptor getServiceDescriptor() {
        return serviceDescriptor;
    }

    public DestinationChanged setServiceDescriptor(ServiceDescriptor serviceDescriptor) {
        this.serviceDescriptor = serviceDescriptor;
        return this;
    }

    public Map<Operation, List<Destination>> getChanges() {
        return changes;
    }

    public DestinationChanged setChanges(Map<Operation, List<Destination>> changes) {
        this.changes = changes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestinationChanged that = (DestinationChanged) o;
        return Objects.equals(serviceDescriptor, that.serviceDescriptor) &&
                Objects.equals(changes, that.changes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceDescriptor, changes);
    }

    @Override
    public String toString() {
        return "DestinationChanged{" +
                "serviceDescriptor=" + serviceDescriptor +
                ", changes=" + changes +
                '}';
    }
}
