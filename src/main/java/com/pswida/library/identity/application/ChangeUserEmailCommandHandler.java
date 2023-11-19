package com.pswida.library.identity.application;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import com.pswida.library.identity.domain.User;
import com.pswida.library.identity.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class ChangeUserEmailCommandHandler extends CommandHandler<ChangeUserEmailCommand> {

  private final UserRepository userRepository;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  @Transactional
  protected void doHandle(ChangeUserEmailCommand command) {
    User user = userRepository.getUserById(command.userId());
    user.updateEmail(command.email());
    user.domainEvents().forEach(eventPublisher::publishEvent);
    userRepository.save(user);
  }

  @Override
  protected Class<ChangeUserEmailCommand> commandType() {
    return ChangeUserEmailCommand.class;
  }
}
