package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.catalog.application.core.validator.ValidationException;
import com.pswida.library.catalog.application.core.validator.ValidationResult;
import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.catalog.domain.book.BookSnapshot;
import com.pswida.library.catalog.domain.book.validator.BookAddValidator;
import lombok.AllArgsConstructor;
import com.pswida.library.catalog.application.core.cqs.command.CommandHandler;
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
