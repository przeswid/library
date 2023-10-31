package com.pswida.library.tracker.infrastructure.db;

import com.pswida.library.tracker.domain.ProcessTrackerId;
import com.pswida.library.tracker.domain.ProcessTrackerRepository;
import com.pswida.library.tracker.domain.ProcessTracker;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
class ProcessTrackerInMemoryRepository implements ProcessTrackerRepository {

  private final Map<ProcessTrackerId, ProcessTracker> trackers = new HashMap<>();

  @Override
  public void save(ProcessTracker processTracker) {
    trackers.put(processTracker.getProcessTrackerId(), processTracker);
  }

  @Override
  public ProcessTracker findById(ProcessTrackerId processTrackerId) {
    return trackers.get(processTrackerId);
  }

  @Override
  public List<ProcessTracker> findTimedOutTrackers() {
    return  trackers.entrySet().stream()
      .map(Map.Entry::getValue)
      .filter(t -> t.isTimeouted())
      .filter(t -> !t.isFinished())
      .toList();
  }
}
