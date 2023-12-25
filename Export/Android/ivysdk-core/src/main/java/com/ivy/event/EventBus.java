package com.ivy.event;

import android.os.Handler;

import com.ivy.util.Logger;

import java.util.ArrayList;
import java.util.Set;

public class EventBus {
  private static final String TAG = EventBus.class.getName();
  private static EventBus instance;
  private final EventListenerList eventListenerList;
  private final Handler handler;

  public EventBus() {
    this(new Handler());
  }

  public EventBus(Handler handler) {
    this.handler = handler;
    this.eventListenerList = new EventListenerList();
  }

  public static EventBus getInstance() {
    if (instance == null) {
      instance = new EventBus();
    }
    return instance;
  }

  public void destroyInstance() {
    removeAllListeners();
    instance = null;
  }

  public void fireEvent(int eventId) {
    fireEvent(eventId, null);
  }

  public void fireEvent(int eventId, Object eventData) {
    Set<EventListener> listeners = this.eventListenerList.getAllListeners(eventId);
    if (listeners == null || listeners.isEmpty()) {
      Logger.debug(TAG, "No listeners to fire eventId=%s on", Integer.valueOf(eventId));
      return;
    }
    Logger.debug(TAG, "Firing eventId=%s on %s listeners", Integer.valueOf(eventId), Integer.valueOf(listeners.size()));
    for (EventListener listener : new ArrayList<>(listeners)) {
      listener.onEvent(eventId, eventData);
    }
  }

  public void postEvent(int eventId) {
    postEvent(eventId, null);
  }

  public void postEvent(final int eventId, final Object eventData) {
    this.handler.post(new Runnable() {
      public void run() {
        EventBus.this.fireEvent(eventId, eventData);
      }
    });
  }

  public void addListener(int eventId, EventListener listener) {
    this.eventListenerList.add(eventId, listener);
  }


  public void removeListener(int eventId, EventListener listener) {
    this.eventListenerList.remove(eventId, listener);
  }


  public void removeAllListeners() {
    this.eventListenerList.clear();
  }
}
