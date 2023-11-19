package com.pswida.library.catalog.infrastructure.events;

import com.pswida.library.catalog.application.book.commands.DiscussionProcessStartCommand;
import com.pswida.library.catalog.domain.book.event.BookCreated;
import com.pswida.library.common.application.cqs.command.CommandDispatcher;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
@AllArgsConstructor
class OnBookingCreatedEventListener {

  private static final String BINDING_NAME = "discussionRequestedProducer";
  private final CommandDispatcher commandDispatcher;

  private final StreamBridge streamBridge;

  @EventListener
  public void handle(BookCreated event) {
    log.info("Handling BookCreated event: {}", event);
    commandDispatcher.dispatch(new DiscussionProcessStartCommand(event.getBookIsbn()));

    Message<DiscussionRequestedEvent> message = MessageBuilder.withPayload(DiscussionRequestedEvent.builder()
      .ownerId(event.getBookIsbn().isbn())
      .eventId(UUID.randomUUID().toString())
      .build()).setHeader(KafkaHeaders.KEY, event.getBookIsbn().isbn().getBytes()).build();

    streamBridge.send(BINDING_NAME, message);

    log.info(String.format("BookCreated sent to Kafka. Isbn: %s... ", message.getPayload().ownerId()));
  }
}
