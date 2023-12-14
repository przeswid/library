package com.pswida.library.identity.application;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import com.pswida.library.identity.domain.User;
import com.pswida.library.identity.domain.UserId;
import com.pswida.library.identity.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
class CreateDiscussionUserCommandHandler extends CommandHandler<CreateDiscussionUserCommand> {

  private final UserRepository userRepository;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  @Transactional
  protected void doHandle(CreateDiscussionUserCommand command) {
    if (userRepository.getUserByUsername(command.username()).isPresent()) {
      throw new RuntimeException("User already exists");
    }
    UserId userId = userRepository.nextUserId();
    User user = User.newUser(userId, command.username(), command.email());
    userRepository.save(user);
    user.domainEvents().forEach(eventPublisher::publishEvent);
  }

  @Override
  protected Class<CreateDiscussionUserCommand> commandType() {
    return CreateDiscussionUserCommand.class;
  }
}
