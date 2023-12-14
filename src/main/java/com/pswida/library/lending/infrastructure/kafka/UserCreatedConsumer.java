package com.pswida.library.lending.infrastructure.kafka;

import com.pswida.library.common.application.cqs.command.CommandDispatcher;
import com.pswida.library.lending.application.CreateLendingUserCommand;
import com.pswida.library.lending.domain.LendingUserId;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Log4j2
@Component("userCreatedConsumer")
@AllArgsConstructor
class UserCreatedConsumer implements Consumer<Message<UserCreatedEvent>> {

  private final CommandDispatcher commandDispatcher;

  @Override
  public void accept(Message<UserCreatedEvent> event) {
    log.info("Received UserCreatedEvent event for userId: " + event.getPayload().id());
    commandDispatcher.dispatch(new CreateLendingUserCommand(new LendingUserId(event.getPayload().id()),
      event.getPayload().username()));
    log.info("Finished processing UserCreatedEvent for userId: " + event.getPayload().id());
  }
}