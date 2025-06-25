package events.eventListener;

import events.Event;
import events.eventTypes.AnswerPostedEvent;

public class AnswerPostedEventListener implements EventListener {

    @Override
    public void update(Event event) {
        AnswerPostedEvent answerPostedEvent = (AnswerPostedEvent) event;
        answerPostedEvent.author().addReputation(answerPostedEvent.question().getBounty());
    }
}
