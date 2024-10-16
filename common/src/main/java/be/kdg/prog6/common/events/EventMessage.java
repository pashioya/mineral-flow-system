package be.kdg.prog6.common.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class EventMessage {
    private EventHeader eventHeader;

    private String eventBody;

    @JsonIgnore
    public static Builder builder() {
        return new Builder();
    }


    private EventMessage(Builder builder) {
        setEventHeader(builder.eventHeader);
        setEventBody(builder.eventBody);
    }

    @JsonIgnoreType
    @NoArgsConstructor
    public static final class Builder {
        private EventHeader eventHeader;
        private String eventBody;

        public Builder eventHeader(EventHeader val) {
            eventHeader = val;
            return this;
        }

        public Builder eventBody(String val) {
            eventBody = val;
            return this;
        }

        public EventMessage build() {
            return new EventMessage(this);
        }
    }
}
