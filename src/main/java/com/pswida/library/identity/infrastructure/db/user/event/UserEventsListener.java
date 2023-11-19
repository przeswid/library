package com.pswida.library.identity.infrastructure.db.user.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pswida.library.identity.domain.EmailChangedEvent;
import com.pswida.library.identity.domain.UserCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class UserEventsListener {

  private final ObjectMapper objectMapper;

  private final UserEventSpringDataRepository userEventSpringDataRepository;

  @EventListener
  void handle(UserCreatedEvent userCreatedEvent) throws JsonProcessingException {
    UserEventEntity userEventEntity = new UserEventEntity(userCreatedEvent.getUserId().id(), "USER_CREATED",
      objectMapper.writeValueAsString(userCreatedEvent));
    userEventSpringDataRepository.save(userEventEntity);
  }

  @EventListener
  void handle(EmailChangedEvent emailChangedEvent) throws JsonProcessingException {
    UserEventEntity userEventEntity = new UserEventEntity(emailChangedEvent.getUserId().id(),
      "USER_EMAIL_CHANGED", objectMapper.writeValueAsString(emailChangedEvent));
    userEventSpringDataRepository.save(userEventEntity);
  }
}
