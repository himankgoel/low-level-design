package events.eventListener;

import events.Event;
import events.eventTypes.AnswerMarkedAsAcceptedEvent;

import static Util.Constants.ANSWER_ACCEPTED_REPUTATION;

public class AnswerMarkedAsAcceptedListener implements EventListener {

    @Override
    public void update(Event event) {
        AnswerMarkedAsAcceptedEvent answerMarkedAsAcceptedEvent = (AnswerMarkedAsAcceptedEvent) event;
        answerMarkedAsAcceptedEvent.answer().getAuthor().addReputation(ANSWER_ACCEPTED_REPUTATION);
        System.out.println("Answer " + answerMarkedAsAcceptedEvent.answer().getContent() + " accepted");
    }
}
