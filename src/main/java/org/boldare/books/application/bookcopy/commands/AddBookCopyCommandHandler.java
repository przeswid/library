package org.boldare.books.application.bookcopy.commands;

import lombok.AllArgsConstructor;
import org.boldare.books.application.core.cqs.command.CommandHandler;
import org.boldare.books.application.core.validator.ValidationException;
import org.boldare.books.application.core.validator.ValidationResult;
import org.boldare.books.domain.bookcopy.BookCopy;
import org.boldare.books.domain.bookcopy.BookCopyRepository;
import org.boldare.books.domain.bookcopy.BookCopySnapshot;
import org.boldare.books.domain.bookcopy.event.BookCopyAdded;
import org.boldare.books.domain.bookcopy.validator.BookCopyAddValidator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AddBookCopyCommandHandler extends CommandHandler<AddBookCopyCommand> {

  private final BookCopyRepository bookCopyRepository;

  private final BookCopyAddValidator validator;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  protected void doHandle(AddBookCopyCommand command) {
    BookCopySnapshot bookCopySnapshot = new BookCopySnapshot(command.bookIsbn(), command.bookCopyId());
    validateBookCopy(bookCopySnapshot);

    BookCopy bookCopy = BookCopy.fromSnapshot(bookCopySnapshot);
    bookCopyRepository.add(bookCopy);
    eventPublisher.publishEvent(new BookCopyAdded(command.bookIsbn(), command.bookCopyId()));
  }

  @Override
  protected Class<AddBookCopyCommand> commandType() {
    return AddBookCopyCommand.class;
  }

  private void validateBookCopy(BookCopySnapshot bookCopySnapshot) {
    ValidationResult validationResult = validator.validate(bookCopySnapshot);
    if (validationResult.status() == ValidationResult.Status.FAILURE) {
      throw new ValidationException(validationResult.errors());
    }
  }
}
