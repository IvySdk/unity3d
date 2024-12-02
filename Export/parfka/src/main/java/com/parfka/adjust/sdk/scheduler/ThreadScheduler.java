package com.parfka.adjust.sdk.scheduler;

public interface ThreadScheduler extends ThreadExecutor {
    void schedule(Runnable task, long millisecondsDelay);
}
