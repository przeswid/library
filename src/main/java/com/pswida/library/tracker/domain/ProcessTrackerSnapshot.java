package com.pswida.library.tracker.domain;

import java.time.Instant;
import java.time.OffsetDateTime;

public record ProcessTrackerSnapshot(
  ProcessTrackerId processTrackerId,
  String processName,
  int retries,
  ProcessTracker.Status status,
  int retriedTimes,
  Instant timeoutAt
) {
}
