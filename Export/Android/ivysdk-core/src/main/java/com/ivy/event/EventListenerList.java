package com.ivy.event;

import com.ivy.util.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventListenerList {
  private static final String TAG = EventListenerList.class.getName();
  private final Map<Integer, Set<EventListener>> listenerMap = new HashMap<>();

  public void add(int eventId, EventListener listener) {
    Set<EventListener> listeners = this.listenerMap.get(eventId);
    if (listeners == null) {
      listeners = new HashSet<>();
      this.listenerMap.put(eventId, listeners);
    }
    if (listeners.add(listener)) {
      Logger.debug(TAG, "Added listener %s for eventId=%s", listener, eventId);
    } else {
      Logger.warning(TAG, "Listener %s for eventId=%s already exist", listener, eventId);
    }
  }

  public void remove(int eventId, EventListener listener) {
    Set<EventListener> listeners = this.listenerMap.get(eventId);
    if (listeners == null) {
      Logger.warning(TAG, "Listener %s for eventId=%s does not exist", listener, eventId);
    } else if (listeners.remove(listener)) {
      Logger.debug(TAG, "Removed listener %s for eventId=%s", listener, eventId);
    } else {
      Logger.warning(TAG, "Listener %s for eventId=%s does not exist", listener, eventId);
    }
  }

  public Set<EventListener> getAllListeners(int eventId) {
    return this.listenerMap.get(eventId);
  }

  public void clear() {
    this.listenerMap.clear();
  }
}
