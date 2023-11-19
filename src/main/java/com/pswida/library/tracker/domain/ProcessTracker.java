package com.pswida.library.tracker.domain;

import com.pswida.library.common.domain.DomainEvent;
import com.pswida.library.tracker.domain.event.ProcessTimedOutEvent;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
public class ProcessTracker {

  private static int nextId = 1;
  @Getter
  private final ProcessTrackerId processTrackerId;
  private final String processName;

  private final int retries;

  private Status status;

  private int retriedTimes;

  private final Instant timeoutAt;

  private final List<DomainEvent> domainEvents = new ArrayList<>();

  public void processTracking() {
    retriedTimes++;
    if (retriedTimes >= retries) {
      endProcess();
      domainEvents.add(new ProcessTimedOutEvent(processTrackerId, true));
    } else {
      domainEvents.add(new ProcessTimedOutEvent(processTrackerId, false));
    }
  }

  public List<DomainEvent> getDomainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

  ProcessTracker(ProcessTrackerId processTrackerId, Status status, String processName, int retries, int retriedTimes,
    Instant timeoutAt) {
    this.processTrackerId = processTrackerId;
    this.processName = processName;
    this.retries = retries;
    this.retriedTimes = retriedTimes;
    this.status = status;
    this.timeoutAt = timeoutAt;
  }

  public ProcessTrackerSnapshot toSnapshot() {
    return new ProcessTrackerSnapshot(processTrackerId, processName, retries, status, retriedTimes, timeoutAt);
  }

  public static ProcessTracker fromSnapshot(ProcessTrackerSnapshot snapshot) {
    return new ProcessTracker(snapshot.processTrackerId(), snapshot.status(), snapshot.processName(),
      snapshot.retries(), snapshot.retriedTimes(), snapshot.timeoutAt());
  }

  public static ProcessTracker startProcess(String processName, int retries, Instant timeoutAt) {
    return new ProcessTracker(new ProcessTrackerId(String.valueOf(nextId++)), Status.STARTED, processName, retries, 0,
      timeoutAt);
  }

  public void endProcess() {
    log.info("End of processing Tracker: " + processTrackerId);
    this.status = Status.FINISHED;
  }

  public boolean isTimeouted() {
    return Instant.now().isAfter(timeoutAt);
  }

  public boolean isFinished() {
    return status == Status.FINISHED;
  }

  public enum Status {
    STARTED, FINISHED
  }
}
