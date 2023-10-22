package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.catalog.application.core.cqs.command.CommandHandler;
import com.pswida.library.catalog.application.core.validator.ValidationException;
import com.pswida.library.catalog.application.core.validator.ValidationResult;
import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.catalog.domain.book.BookSnapshot;
import com.pswida.library.catalog.domain.book.validator.BookAddValidator;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AddBookCommandHandler extends CommandHandler<AddBookCommand> {

  private final BookRepository bookRepository;

  private final BookAddValidator validator;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  protected void doHandle(AddBookCommand command) {

    BookSnapshot bookSnapshot = command.book();
    validateBook(bookSnapshot);

    Book book = Book.newBook(bookSnapshot.title(), bookSnapshot.isbn(), bookSnapshot.authors(), bookSnapshot.bookCategory());
    bookRepository.save(book);
    book.getDomainEvents().forEach(eventPublisher::publishEvent);
  }

  private void validateBook(BookSnapshot bookSnapshot) {
    ValidationResult validate = validator.validate(bookSnapshot);
    if (validate.status() == ValidationResult.Status.FAILURE) {
      throw new ValidationException(validate.errors());
    }
  }

  @Override
  protected Class<AddBookCommand> commandType() {
    return AddBookCommand.class;
  }

}
