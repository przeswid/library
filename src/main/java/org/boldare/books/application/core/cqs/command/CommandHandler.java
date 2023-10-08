package org.boldare.books.application.core.cqs.command;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.StopWatch;

@Log4j2
public abstract class CommandHandler<T extends Command> {

  public void handle(T command) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    try {
      doHandle(command);
    } catch (Exception e) {
      log.error("Error while handling command: " + command, e);
      throw e;
    }

    stopWatch.stop();
    log.debug("Command {} handled in {} ms", command, stopWatch.getTotalTimeMillis());
  }

  protected abstract void doHandle(T command);

  protected abstract Class<T> commandType();
}
