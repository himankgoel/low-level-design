package events;

import events.eventListener.EventListener;
import events.eventTypes.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager {

    Map<EventType, List<EventListener>> subscribers;

    public EventManager() {
        subscribers = new ConcurrentHashMap<>();
    }

    public void subscribe(final EventType eventType, final EventListener eventListener) {
        if (!subscribers.containsKey(eventType)) {
            subscribers.put(eventType, new ArrayList<>());
        }
        subscribers.get(eventType).add(eventListener);
    }

    public void notify(final Event event) {
        subscribers.get(event.getEventType()).forEach(eventListener -> eventListener.update(event));
    }
}
