package com.pswida.library.discussion.infrastructure.kafka;

import com.pswida.library.common.application.cqs.command.CommandDispatcher;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Consumer;

@Log4j2
@Component("userEventConsumer")
@AllArgsConstructor
class UserEventsConsumer implements Consumer<Message<UserEventMessage>> {

  private static final String MESSAGE_HEADER_EVENT_TYPE = "event_type";
  private final CommandDispatcher commandDispatcher;
  private final CommandFactory commandFactory;

  @Override
  public void accept(Message<UserEventMessage> event) {
    String eventType = getEventType(event.getHeaders());
    log.info("Received UserEvent event type: {}, userId: {}", eventType, event.getPayload().userId());
    commandDispatcher.dispatch(commandFactory.createCommand(eventType, event.getPayload().payload()));
  }

  private String getEventType(MessageHeaders h) {
    return Optional.ofNullable(h)
      .filter(headers -> headers.containsKey(MESSAGE_HEADER_EVENT_TYPE))
      .map(messageHeaders -> messageHeaders.get(MESSAGE_HEADER_EVENT_TYPE))
      .map(v -> new String((byte[]) v, StandardCharsets.UTF_8))
      .orElseThrow(() -> new IllegalArgumentException(
        "Message doesn't contain a header with key: '" + MESSAGE_HEADER_EVENT_TYPE + "' or header is corrupted"));
  }
}