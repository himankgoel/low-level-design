package events.eventTypes;

import entities.Votable;
import events.Event;

public record VotableVotedEvent(Votable votable) implements Event {

    @Override
    public EventType getEventType() {
        return EventType.VOTABLE_VOTED;
    }
}
