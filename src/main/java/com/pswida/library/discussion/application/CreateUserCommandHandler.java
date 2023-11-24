package com.pswida.library.discussion.application;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import com.pswida.library.discussion.domain.Discussion;
import com.pswida.library.discussion.domain.DiscussionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
class CreateUserCommandHandler extends CommandHandler<CreateUserCommand> {

  @Override
  protected void doHandle(CreateUserCommand command) {
    log.info("User created event: {}", command);
  }

  @Override
  protected Class<CreateUserCommand> commandType() {
    return CreateUserCommand.class;
  }

}
