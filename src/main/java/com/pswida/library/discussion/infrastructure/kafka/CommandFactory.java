package com.pswida.library.discussion.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.discussion.application.CreateUserCommand;
import com.pswida.library.discussion.application.UpdateUserEmailCommand;
import com.pswida.library.discussion.domain.user.UserId;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
class CommandFactory {

  private final ObjectMapper objectMapper;

  private final Map<String, Function<String, Command>> commandsByEventType = Map.of(
    "USER_CREATED", this::createUserCommand,
    "USER_EMAIL_CHANGED", this::userEmailChangedCommand);

  public CommandFactory(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public Command createCommand(String eventType, String payload) {
    return commandsByEventType.get(eventType).apply(payload);
  }

  private Command createUserCommand(String payload) {
    try {
      UserCreatedEvent userCreatedEvent = objectMapper.readValue(payload, UserCreatedEvent.class);
      return new CreateUserCommand(userCreatedEvent.getUserId(), userCreatedEvent.getUsername(),
        userCreatedEvent.getEmail());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private Command userEmailChangedCommand(String payload) {
    try {
      UserEmailChanged userCreatedEvent = objectMapper.readValue(payload, UserEmailChanged.class);
      return new UpdateUserEmailCommand(userCreatedEvent.getUserId(), userCreatedEvent.getEmail());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Getter
  static class UserCreatedEvent {
    private UserId userId;
    private String username;
    private String email;
  }

  @Getter
  static class UserEmailChanged {
    private UserId userId;
    private String email;
  }

}
