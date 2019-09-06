package com.openmatics.testinstrumentation.platform.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.joda.time.DateTime;

public class EventEnvelope<E> {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonUnwrapped
    private EventHeader header;

    private E payload;

    @JsonCreator
    public EventEnvelope(@JsonProperty("eventID") String eventID, @JsonProperty("eventTimestamp") DateTime eventTimestamp, @JsonProperty("sourceName") String sourceName,
                         @JsonProperty("causeEventID") String causeEventId, @JsonProperty("correlationId") String correlationId,
                         @JsonProperty("sagaId") String sagaId, @JsonProperty("payload") E payload) {
        this.header = new EventHeader(eventID, eventTimestamp, sourceName, causeEventId, correlationId, sagaId);
        this.payload = payload;
    }

    public EventEnvelope(EventHeader header, @JsonProperty("payload") E payload) {
        this(header.getEventID(), header.getEventTimestamp(), header.getSourceName(), header.getCauseEventID(), header.getCorrelationId(), header.getSagaId(), payload);
    }

    public EventHeader getHeader() {
        return header;
    }

    public E getPayload() {
        return payload;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((header == null) ? 0 : header.hashCode());
        result = prime * result + ((payload == null) ? 0 : payload.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventEnvelope other = (EventEnvelope) obj;
        if (header == null) {
            if (other.header != null)
                return false;
        } else if (!header.equals(other.header))
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
        return "EventEnvelope [header=" + header + ", payload=" + payload + "]";
    }

}
