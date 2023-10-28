package com.pswida.library.common.application.cqs.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CommandDispatcher {

  private final List<CommandHandler<?>> commandHandlers;

  public void dispatch(Command command) {
    commandHandlers.stream()
      .filter(handler -> handler.commandType().equals(command.getClass()))
      .findFirst()
      .map(handler -> (CommandHandler<Command>) handler)
      .orElseThrow(() -> new RuntimeException("No handler found for command: " + command))
      .handle(command);
  }
}
