package com.openmatics.testinstrumentation.platform.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class EventHeader {

    private String eventID;


    private DateTime eventTimestamp;

    private String sourceName;

    @JsonInclude(Include.NON_NULL)
    private String causeEventID;

    private String correlationId;

    private String sagaId;

    public EventHeader(@JsonProperty("eventID") String eventID, @JsonProperty("eventTimestamp") DateTime eventTimestamp, @JsonProperty("sourceName") String sourceName,
                       @JsonProperty("causeEventId") String causeEventId, @JsonProperty("correlationId") String correlationId, @JsonProperty("sagaId") String sagaId) {
        this.eventID = eventID;
        this.eventTimestamp = eventTimestamp;
        this.sourceName = sourceName;
        this.causeEventID = causeEventId;
        this.correlationId = correlationId;
        this.sagaId = sagaId;
    }

    public EventHeader(@JsonProperty("eventID") String eventID, @JsonProperty("eventTimestamp") DateTime eventTimestamp, @JsonProperty("sourceName") String sourceName,
                       @JsonProperty("causeEventId") String causeEventId) {
        this.eventID = eventID;
        this.eventTimestamp = eventTimestamp;
        this.sourceName = sourceName;
        this.causeEventID = causeEventId;
    }

    public String getEventID() {
        return eventID;
    }

    public DateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getCauseEventID() {
        return causeEventID;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getSagaId() {
        return sagaId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eventID == null) ? 0 : eventID.hashCode());
        result = prime * result + ((eventTimestamp == null) ? 0 : eventTimestamp.hashCode());
        result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
        result = prime * result + ((correlationId == null) ? 0 : correlationId.hashCode());
        result = prime * result + ((sagaId == null) ? 0 : sagaId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventHeader that = (EventHeader) o;

        if (!eventID.equals(that.eventID)) return false;
        if (!eventTimestamp.equals(that.eventTimestamp)) return false;
        if (!sourceName.equals(that.sourceName)) return false;
        if (!sagaId.equals(that.sagaId)) return false;
        return correlationId.equals(that.correlationId);
    }

    @Override
    public String toString() {
        return "EventHeader{" +
                "eventID='" + eventID + '\'' +
                ", eventTimestamp=" + eventTimestamp +
                ", sourceName='" + sourceName + '\'' +
                ", causeEventID='" + causeEventID + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", sagaId='" + sagaId + '\'' +
                '}';
    }
}
