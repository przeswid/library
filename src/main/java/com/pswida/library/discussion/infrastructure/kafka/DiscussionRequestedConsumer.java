package com.pswida.library.discussion.infrastructure.kafka;

import com.pswida.library.common.application.cqs.command.CommandDispatcher;
import com.pswida.library.discussion.application.CreateDiscussionCommand;
import com.pswida.library.discussion.domain.DiscussionOwnerId;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Log4j2
@Component("discussionRequestedConsumer")
@AllArgsConstructor
class DiscussionRequestedConsumer implements Consumer<Message<DiscussionRequestedEvent>> {

  private final CommandDispatcher commandDispatcher;

  @Override
  public void accept(Message<DiscussionRequestedEvent> event) {
    log.info("Received DiscussionRequestedEvent event for owner: " + event.getPayload().ownerId());
    commandDispatcher.dispatch(new CreateDiscussionCommand(new DiscussionOwnerId(event.getPayload().ownerId())));
    log.info("Finished processing DiscussionRequestedEvent for owner: " + event.getPayload().ownerId());
  }
}