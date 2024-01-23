package com.ivy.ads.utils;

public class Stopwatch {
  private long mCurrentDuration = 0;
  private boolean mRunning = false;
  private long mStartTime = 0;

  public Stopwatch(String name) {
  }

  public synchronized void resume() {
    if (!this.mRunning) {
      this.mStartTime = System.currentTimeMillis();
      this.mRunning = !this.mRunning;
    }
  }

  public synchronized void clear() {
    this.mRunning = false;
    this.mCurrentDuration = 0;
  }

  public synchronized void pause() {
    if (this.mRunning) {
      this.mCurrentDuration += System.currentTimeMillis() - this.mStartTime;
      this.mRunning = !this.mRunning;
    }
  }

  public synchronized long getElapsedTimeMillis() {
    updateDuration();
    return this.mCurrentDuration;
  }

  private synchronized void updateDuration() {
    if (this.mRunning) {
      this.mCurrentDuration += System.currentTimeMillis() - this.mStartTime;
      this.mStartTime = System.currentTimeMillis();
    }
  }

  public synchronized void restart() {
    clear();
    resume();
  }
}
