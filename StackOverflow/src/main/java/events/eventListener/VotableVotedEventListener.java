package events.eventListener;

import events.Event;
import events.eventTypes.VotableVotedEvent;

public class VotableVotedEventListener implements EventListener {

    @Override
    public void update(Event event) {
        VotableVotedEvent votableVotedEvent = (VotableVotedEvent) event;
        System.out.println(votableVotedEvent.votable().getClass().getSimpleName());
    }
}
