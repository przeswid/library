package com.pswida.library.catalog.application.bookcopy.commands;

import com.pswida.library.catalog.application.core.time.DateTimeProvider;
import com.pswida.library.catalog.domain.bookcopy.BookCopy;
import com.pswida.library.catalog.domain.bookcopy.BookCopyRepository;
import lombok.AllArgsConstructor;
import com.pswida.library.catalog.application.core.cqs.command.CommandHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class BorrowBookCopyCommandHandler extends CommandHandler<BorrowBookCopyCommand> {

  private final BookCopyRepository bookCopyRepository;

  private final DateTimeProvider realDateTimeProvider;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  protected void doHandle(BorrowBookCopyCommand command) {
    BookCopy bookCopy = bookCopyRepository.getById(command.bookCopyId());
    bookCopy.borrowBookCopy(realDateTimeProvider.getCurrentDateTime());
    bookCopy.domainEvents().forEach(eventPublisher::publishEvent);
  }

  @Override
  protected Class<BorrowBookCopyCommand> commandType() {
    return BorrowBookCopyCommand.class;
  }
}
