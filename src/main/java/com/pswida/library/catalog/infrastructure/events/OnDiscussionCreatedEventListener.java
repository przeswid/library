package com.pswida.library.catalog.infrastructure.events;

import com.pswida.library.catalog.application.book.commands.InitiateDiscussionCommand;
import com.pswida.library.common.application.cqs.command.CommandDispatcher;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.discussion.domain.DiscussionId;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Log4j2
@Component("discussionCreatedConsumer")
@AllArgsConstructor
class OnDiscussionCreatedEventListener implements Consumer<Message<DiscussionCreatedKafkaEvent>> {

  private final CommandDispatcher commandDispatcher;

  @Override
  public void accept(Message<DiscussionCreatedKafkaEvent> event) {
    log.info("Received Kafka event: " + event);
    commandDispatcher.dispatch(new InitiateDiscussionCommand(new BookIsbn(event.getPayload().ownerId()),
      new DiscussionId(event.getPayload().discussionId())));
  }
}