package com.pswida.library.tracker.domain;

import java.util.List;

public interface ProcessTrackerRepository {
  void save(ProcessTracker processTracker);
  ProcessTracker findById(ProcessTrackerId processTrackerId);
  List<ProcessTracker> findTimedOutTrackers();
}
