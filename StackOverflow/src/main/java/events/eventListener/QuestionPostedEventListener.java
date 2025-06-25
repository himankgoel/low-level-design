package events.eventListener;

import events.Event;
import events.eventTypes.QuestionPostedEvent;

public class QuestionPostedEventListener implements EventListener {

    @Override
    public void update(Event event) {
        QuestionPostedEvent questionPostedEvent = (QuestionPostedEvent) event;
        System.out.println(questionPostedEvent.user().getUsername() + " asked " + questionPostedEvent.question().getTitle()
                + " with id: " + questionPostedEvent.question().getId());
    }
}
