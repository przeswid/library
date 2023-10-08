package org.boldare.books.application.bookcopy.commands;

import lombok.AllArgsConstructor;
import org.boldare.books.application.core.cqs.command.CommandHandler;
import org.boldare.books.application.core.time.DateTimeProvider;
import org.boldare.books.domain.bookcopy.BookCopy;
import org.boldare.books.domain.bookcopy.BookCopyRepository;
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
