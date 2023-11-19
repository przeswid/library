package com.pswida.library.lending.application;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import com.pswida.library.lending.domain.LendingUser;
import com.pswida.library.lending.domain.LendingUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class CreateLendingUserCommandHandler extends CommandHandler<CreateLendingUserCommand> {

  private final LendingUserRepository lendingUserRepository;

  @Override
  protected void doHandle(CreateLendingUserCommand command) {
    LendingUser lendingUser = LendingUser.createUser(command.id(), command.username());

    lendingUserRepository.save(lendingUser);
  }

  @Override
  protected Class<CreateLendingUserCommand> commandType() {
    return CreateLendingUserCommand.class;
  }
}
