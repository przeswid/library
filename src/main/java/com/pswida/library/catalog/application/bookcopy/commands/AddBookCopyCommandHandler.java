package com.pswida.library.catalog.application.bookcopy.commands;

import com.pswida.library.catalog.application.core.cqs.command.CommandHandler;
import com.pswida.library.catalog.application.core.validator.ValidationException;
import com.pswida.library.catalog.application.core.validator.ValidationResult;
import com.pswida.library.catalog.domain.bookcopy.BookCopy;
import com.pswida.library.catalog.domain.bookcopy.BookCopyRepository;
import com.pswida.library.catalog.domain.bookcopy.BookCopySnapshot;
import com.pswida.library.catalog.domain.bookcopy.event.BookCopyAdded;
import com.pswida.library.catalog.domain.bookcopy.validator.BookCopyAddValidator;
import lombok.AllArgsConstructor;
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
