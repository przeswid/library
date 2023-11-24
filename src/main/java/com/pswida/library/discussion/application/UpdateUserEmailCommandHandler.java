package com.pswida.library.discussion.application;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
class UpdateUserEmailCommandHandler extends CommandHandler<UpdateUserEmailCommand> {

  @Override
  protected void doHandle(UpdateUserEmailCommand command) {
    log.info("User email changed to: {}", command);
  }

  @Override
  protected Class<UpdateUserEmailCommand> commandType() {
    return UpdateUserEmailCommand.class;
  }

}
