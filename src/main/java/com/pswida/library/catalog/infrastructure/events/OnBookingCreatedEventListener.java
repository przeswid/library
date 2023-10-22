package com.pswida.library.catalog.infrastructure.events;

import com.pswida.library.catalog.application.book.commands.DiscussionProcessStartCommand;
import com.pswida.library.catalog.application.core.cqs.command.CommandDispatcher;
import com.pswida.library.catalog.domain.book.event.BookCreated;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class OnBookingCreatedEventListener {

  private final CommandDispatcher commandDispatcher;

  @EventListener
  public void handle(BookCreated event) {
    log.info("Handling BookCreated event: {}", event);
    commandDispatcher.dispatch(new DiscussionProcessStartCommand(event.getBookIsbn()));

    // Send Domain Event to Kafka
  }
}
