package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.common.application.cqs.command.CommandHandler;
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

    Book book = Book.bookWithRequestedDiscussion(bookSnapshot.getTitle(), bookSnapshot.getIsbn(), bookSnapshot.getAuthors(), bookSnapshot.getBookCategory());
    bookRepository.save(book);
    book.getDomainEvents().forEach(eventPublisher::publishEvent);
  }

  private void validateBook(BookSnapshot bookSnapshot) {
//    ValidationResult validate = validator.validate(bookSnapshot);
//    if (validate.status() == ValidationResult.Status.FAILURE) {
//      throw new ValidationException(validate.errors());
//    }
  }

  @Override
  protected Class<AddBookCommand> commandType() {
    return AddBookCommand.class;
  }

}
