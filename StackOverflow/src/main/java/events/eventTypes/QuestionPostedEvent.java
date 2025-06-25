package events.eventTypes;

import entities.Question;
import entities.User;
import events.Event;

import static events.eventTypes.EventType.QUESTION_POSTED;

public record QuestionPostedEvent(Question question, User user) implements Event {

    @Override
    public EventType getEventType() {
        return QUESTION_POSTED;
    }
}
