package com.openmatics.testinstrumentation.platform.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class EventOutcome<T> {

    private Operation operation;
    private Status status;

    @JsonInclude(Include.NON_NULL)
    private String failMessage;
    private T payload;

    public EventOutcome(@JsonProperty("operation") Operation operation, @JsonProperty("status") Status status, @JsonProperty("failMessage") String failMessage,
                        @JsonProperty("payload") T payload) {
        this.operation = operation;
        this.status = status;
        this.failMessage = failMessage;
        this.payload = payload;
    }

    public EventOutcome(Operation operation, T payload) {
        this(operation, null, null, payload);
    }

    public EventOutcome(Operation operation, Status status, T payload) {
        this(operation, status, null, payload);
    }

    public Operation getOperation() {
        return operation;
    }


    public String getFailMessage() {
        return failMessage;
    }

    public T getPayload() {
        return payload;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "EventOutcome{" +
                "operation=" + operation +
                ", status=" + status +
                ", failMessage='" + failMessage + '\'' +
                ", payload=" + payload +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventOutcome<?> that = (EventOutcome<?>) o;
        return operation == that.operation &&
                status == that.status &&
                Objects.equals(failMessage, that.failMessage) &&
                Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, status, failMessage, payload);
    }
}
