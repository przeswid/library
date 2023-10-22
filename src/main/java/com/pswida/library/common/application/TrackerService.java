package com.pswida.library.common.application;

import com.pswida.library.common.domain.tracker.ProcessTrackerRepository;
import com.pswida.library.common.domain.tracker.TimeConstrainedProcessTracker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class TrackerService {

  private final ProcessTrackerRepository processTrackerRepository;

  private final ApplicationEventPublisher eventPublisher;

  @Scheduled(fixedDelay = 1000)
  void checkProcesses() {
    log.debug("Checking processes");

    List<TimeConstrainedProcessTracker> trackers = processTrackerRepository.findTimeOutTrackers();

    for (TimeConstrainedProcessTracker tracker : trackers) {
      log.info("Found tracker: " + tracker);
      tracker.processTracking();
      tracker.getDomainEvents().forEach(eventPublisher::publishEvent);
    }

  }

}
