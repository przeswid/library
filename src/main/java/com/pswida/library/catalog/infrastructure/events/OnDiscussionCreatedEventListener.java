package com.pswida.library.catalog.infrastructure.events;

import com.pswida.library.catalog.application.book.commands.InitiateDiscussionCommand;
import com.pswida.library.catalog.application.core.cqs.command.CommandDispatcher;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.discussion.domain.event.DiscussionCreated;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class OnDiscussionCreatedEventListener {

  private final CommandDispatcher commandDispatcher;

  @EventListener
  public void handle(DiscussionCreated event) {
    log.info("Handling DiscussionCreated event: {}", event);
    commandDispatcher.dispatch(
      new InitiateDiscussionCommand(new BookIsbn(event.getOwnerId().id()), event.getDiscussionId()));
  }
}
