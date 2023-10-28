package com.pswida.library.tracker.domain.event;

import com.pswida.library.common.domain.DomainEvent;
import com.pswida.library.tracker.domain.ProcessTrackerId;
import lombok.Value;

@Value
public class ProcessTimedOutEvent extends DomainEvent {

  ProcessTrackerId trackerId;

  boolean retriedMaxTimes;

}
