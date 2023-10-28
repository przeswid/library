package com.pswida.library.discussion.infrastructure.kafka;

import com.pswida.library.discussion.domain.event.DiscussionCreated;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
class DiscussionCreatedKafkaProducer {

  private static final String BINDING_NAME = "discussionCreatedProducer";

  private final StreamBridge streamBridge;

  @EventListener
  public void handle(DiscussionCreated event) {
    log.info("Handling DiscussionCreated event: {}", event);

    Message<DiscussionCreatedKafkaEvent> message = MessageBuilder.withPayload(DiscussionCreatedKafkaEvent.builder()
      .discussionId(event.getDiscussionId().id())
      .ownerId(event.getOwnerId().id())
      .build()).setHeader(KafkaHeaders.KEY, event.getOwnerId().id().getBytes()).build();

    streamBridge.send(BINDING_NAME, message);

    log.info(String.format("DiscussionCreated sent to Kafka: %s... ", message));
  }
}
