package com.pswida.library.tracker.infrastructure.db.document;

import com.pswida.library.tracker.domain.ProcessTracker;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.OffsetDateTime;

@Document(collection = "process-tracker")
@Data
public class ProcessTrackerDocument {

  @Id
  private String processTrackerId;
  private String processName;

  private int retries;

  private ProcessTracker.Status status;

  private int retriedTimes;

  private final Instant timeoutAt;

}
