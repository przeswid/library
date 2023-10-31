package com.pswida.library.tracker.application;

import com.pswida.library.tracker.domain.ProcessTrackerRepository;
import com.pswida.library.tracker.domain.ProcessTracker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
class TrackerService {

  private final ProcessTrackerRepository processTrackerRepository;

  private final ApplicationEventPublisher eventPublisher;

  @Scheduled(fixedDelay = 5000)
  void processTimedOutProcesses() {

    List<ProcessTracker> trackers = processTrackerRepository.findTimedOutTrackers();

    for (ProcessTracker tracker : trackers) {
      log.info("Found tracker: " + tracker.getProcessTrackerId());
      tracker.processTracking();
      tracker.getDomainEvents().forEach(eventPublisher::publishEvent);
    }

  }

}
