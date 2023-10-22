package com.pswida.library.common.domain.tracker.event;

import com.pswida.library.common.domain.DomainEvent;
import com.pswida.library.common.domain.tracker.ProcessTrackerId;
import lombok.Value;

@Value
public class ProcessTimedOutEvent extends DomainEvent {

  ProcessTrackerId trackerId;

  boolean retriedMaxTimes;

}
