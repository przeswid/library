package org.boldare.books.application.book.commands;

import lombok.AllArgsConstructor;
import org.boldare.books.application.core.cqs.command.CommandHandler;
import org.boldare.books.application.core.validator.ValidationException;
import org.boldare.books.application.core.validator.ValidationResult;
import org.boldare.books.domain.book.Book;
import org.boldare.books.domain.book.BookRepository;
import org.boldare.books.domain.book.BookSnapshot;
import org.boldare.books.domain.book.validator.BookAddValidator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AddBookCommandHandler extends CommandHandler<AddBookCommand> {

  private final BookRepository bookRepository;

  private final BookAddValidator validator;

  @Override
  protected void doHandle(AddBookCommand command) {
    validateBook(command.book());

    Book book = Book.fromSnapshot(command.book());
    bookRepository.add(book);
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
