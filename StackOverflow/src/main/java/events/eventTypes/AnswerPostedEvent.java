package events.eventTypes;

import entities.Answer;
import entities.Question;
import entities.User;
import events.Event;

import static events.eventTypes.EventType.ANSWER_POSTED;

public record AnswerPostedEvent(Question question, Answer answer, User author) implements Event {

    @Override
    public EventType getEventType() {
        return ANSWER_POSTED;
    }
}
