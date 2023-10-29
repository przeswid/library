package com.pswida.library.lending.application;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import com.pswida.library.lending.domain.BookAvailabilityDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class LendABookCommandHandler extends CommandHandler<LendABookCommand> {

  private final BookAvailabilityDomainService bookAvailabilityValidator;

  @Override
  protected void doHandle(LendABookCommand command) {
    if (!bookAvailabilityValidator.isAvailable(command.bookCopyId())) {
      throw new IllegalStateException("Book is not available to lend");
    }

  }

  @Override
  protected Class<LendABookCommand> commandType() {
    return LendABookCommand.class;
  }
}
