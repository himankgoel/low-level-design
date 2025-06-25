package events.eventTypes;

import entities.Answer;
import events.Event;

import static events.eventTypes.EventType.ANSWER_MARKED_AS_ACCEPTED;

public record AnswerMarkedAsAcceptedEvent (Answer answer) implements Event {

    @Override
    public EventType getEventType() {
        return ANSWER_MARKED_AS_ACCEPTED;
    }
}
