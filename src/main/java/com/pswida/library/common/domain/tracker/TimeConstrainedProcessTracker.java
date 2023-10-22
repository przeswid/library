package com.pswida.library.common.domain.tracker;

import com.pswida.library.common.domain.DomainEvent;
import com.pswida.library.common.domain.tracker.event.ProcessTimedOutEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TimeConstrainedProcessTracker {

  @Getter
  private final ProcessTrackerId processTrackerId;
  private final String processName;

  private final int retries;

  private Status status;

  private int retriedTimes;

  private final List<DomainEvent> domainEvents = new ArrayList<>();

  public void processTracking() {
    domainEvents.add(new ProcessTimedOutEvent(processTrackerId, false));
  }

  public List<DomainEvent> getDomainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

  TimeConstrainedProcessTracker(ProcessTrackerId processTrackerId, Status status, String processName, int retries,
    int retriedTimes) {
    this.processTrackerId = processTrackerId;
    this.processName = processName;
    this.retries = retries;
    this.retriedTimes = retriedTimes;
    this.status = status;
  }

  public static TimeConstrainedProcessTracker startProcess(String processName, int retries) {
    return new TimeConstrainedProcessTracker(new ProcessTrackerId(UUID.randomUUID().toString()), Status.STARTED,
      processName, retries, 0);
  }

  public void endProcess() {
    this.status = Status.FINISHED;
  }

  public enum Status {
    STARTED, FINISHED
  }
}
