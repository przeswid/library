package com.pswida.library.tracker.infrastructure.db;

import com.pswida.library.tracker.domain.ProcessTracker;
import com.pswida.library.tracker.domain.ProcessTrackerId;
import com.pswida.library.tracker.domain.ProcessTrackerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
class ProcessTrackerMongoDbRepository implements ProcessTrackerRepository {

  private final ProcessTrackerSpringDataRepository springDataRepository;

  private final ProcessTrackerMapper mapper;

  @Override
  public void save(ProcessTracker processTracker) {
    springDataRepository.save(
      mapper.mapProcessTrackerSnapshotToProcessTrackerDocument(processTracker.toSnapshot()));
  }

  @Override
  public ProcessTracker findById(ProcessTrackerId processTrackerId) {
    return springDataRepository.findById(processTrackerId.id())
      .map(mapper::mapProcessTrackerDocumentToProcessTrackerSnapshot)
      .map(ProcessTracker::fromSnapshot)
      .orElseThrow();
  }

  @Override
  public List<ProcessTracker> findTimedOutTrackers() {
    return springDataRepository.findAll()
      .stream()
      .map(mapper::mapProcessTrackerDocumentToProcessTrackerSnapshot)
      .map(ProcessTracker::fromSnapshot)
      .filter(ProcessTracker::isTimeouted)
      .filter(t -> !t.isFinished())
      .toList();
  }
}
