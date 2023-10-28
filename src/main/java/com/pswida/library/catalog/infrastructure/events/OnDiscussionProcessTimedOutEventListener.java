package com.pswida.library.catalog.infrastructure.events;

import com.pswida.library.catalog.application.book.commands.DiscussionProcessTimedOutCommand;
import com.pswida.library.common.application.cqs.command.CommandDispatcher;
import com.pswida.library.tracker.domain.event.ProcessTimedOutEvent;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
class OnDiscussionProcessTimedOutEventListener {

  private final CommandDispatcher commandDispatcher;

  @EventListener
  public void handle(ProcessTimedOutEvent event) {
    log.info("Handling ProcessTimedOutEvent tracker.id: {}", event.getTrackerId());

    commandDispatcher.dispatch(
      new DiscussionProcessTimedOutCommand(event.getTrackerId(), event.isRetriedMaxTimes()));
  }
}
