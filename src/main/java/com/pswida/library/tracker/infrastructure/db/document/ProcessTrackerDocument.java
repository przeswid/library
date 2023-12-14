package com.pswida.library.tracker.infrastructure.db.document;

import com.pswida.library.tracker.domain.ProcessTracker;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(schema = "tracker", name = "process_tracker")
@Data
@NoArgsConstructor
public class ProcessTrackerDocument {

  @Id
  private String processTrackerId;
  private String processName;

  private int retries;

  private ProcessTracker.Status status;

  private int retriedTimes;

  private Instant timeoutAt;

}
