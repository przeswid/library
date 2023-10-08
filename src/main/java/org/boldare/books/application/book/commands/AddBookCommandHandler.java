package org.boldare.books.application.book.commands;

import lombok.AllArgsConstructor;
import org.boldare.books.application.core.cqs.command.CommandHandler;
import org.boldare.books.domain.book.Book;
import org.boldare.books.domain.book.BookIsbn;
import org.boldare.books.domain.book.BookRepository;
import org.boldare.books.domain.book.BookSnapshot;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class AddBookCommandHandler extends CommandHandler<AddBookCommand> {

  private final BookRepository bookRepository;

  @Override
  protected void doHandle(AddBookCommand command) {
    validateBookDoesNotExist(command.book());

    Book book = Book.fromSnapshot(command.book());
    bookRepository.add(book);
  }

  @Override
  protected Class<AddBookCommand> commandType() {
    return AddBookCommand.class;
  }

  private void validateBookDoesNotExist(BookSnapshot bookSnapshot) {
    BookIsbn isbn = bookSnapshot.isbn();
    Optional<Book> book = bookRepository.getByIsbn(isbn);

    if (book.isPresent()) {
      throw new IllegalStateException("Book with isbn: " + isbn + " already exists");
    }
  }

}
