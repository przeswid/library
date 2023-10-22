package com.pswida.library.common.domain.tracker;

import java.util.List;

public interface ProcessTrackerRepository {
  void save(TimeConstrainedProcessTracker processTracker);
  TimeConstrainedProcessTracker findById(ProcessTrackerId processTrackerId);
  List<TimeConstrainedProcessTracker> findTimeOutTrackers();
}
