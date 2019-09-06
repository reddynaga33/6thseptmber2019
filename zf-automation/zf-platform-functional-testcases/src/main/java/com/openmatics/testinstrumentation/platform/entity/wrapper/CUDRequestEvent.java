package com.openmatics.testinstrumentation.platform.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CUDRequestEvent<T> {

    private Operation operation;

    private T payload;

    public CUDRequestEvent(@JsonProperty("operation") Operation operation, @JsonProperty("payload") T payload) {
        this.operation = operation;
        this.payload = payload;
    }

    public Operation getOperation() {
        return operation;
    }

    public T getPayload() {
        return payload;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        result = prime * result + ((payload == null) ? 0 : payload.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CUDRequestEvent<?> other = (CUDRequestEvent<?>) obj;
        if (operation != other.operation)
            return false;
        if (payload == null) {
            if (other.payload != null)
                return false;
        } else if (!payload.equals(other.payload))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CRUDRequest [operation=" + operation + ", payload=" + payload + "]";
    }

}
