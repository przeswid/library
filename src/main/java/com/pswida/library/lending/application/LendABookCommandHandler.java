package com.pswida.library.lending.application;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import com.pswida.library.lending.domain.BookAvailabilityService;
import com.pswida.library.lending.domain.Lending;
import com.pswida.library.lending.domain.LendingId;
import com.pswida.library.lending.domain.LendingRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
class LendABookCommandHandler extends CommandHandler<LendABookCommand> {

  private final BookAvailabilityService bookAvailabilityService;

  private final LendingRepository lendingRepository;

  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  protected void doHandle(LendABookCommand command) {
    if (!bookAvailabilityService.isAvailable(command.bookCopyId())) {
      throw new IllegalStateException("Book is not available to lend");
    }
    String lendingId = UUID.randomUUID().toString();
    Lending lending = Lending.createLending(new LendingId(lendingId), command.bookCopyId());

    lendingRepository.save(lending);
    lending.domainEvents().forEach(applicationEventPublisher::publishEvent);
  }

  @Override
  protected Class<LendABookCommand> commandType() {
    return LendABookCommand.class;
  }
}
