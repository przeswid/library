package org.boldare.books.application.bookcopy.commands;

import lombok.AllArgsConstructor;
import org.boldare.books.application.core.cqs.command.CommandHandler;
import org.boldare.books.domain.book.BookRepository;
import org.boldare.books.domain.bookcopy.BookCopy;
import org.boldare.books.domain.bookcopy.BookCopyRepository;
import org.boldare.books.domain.bookcopy.BookCopySnapshot;
import org.boldare.books.domain.bookcopy.event.BookCopyAdded;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AddBookCopyCommandHandler extends CommandHandler<AddBookCopyCommand> {

  private final BookCopyRepository bookCopyRepository;

  private final BookRepository bookRepository;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  protected void doHandle(AddBookCopyCommand command) {
    bookRepository.getByIsbn(command.bookIsbn())
      .orElseThrow(() -> new IllegalStateException("Book not found"));

    BookCopy bookCopy = BookCopy.fromSnapshot(
      new BookCopySnapshot(command.bookIsbn(), command.bookCopyId(), false, null, null, null));
    bookCopyRepository.add(bookCopy);
    eventPublisher.publishEvent(new BookCopyAdded(command.bookIsbn(), command.bookCopyId()));
  }

  @Override
  protected Class<AddBookCopyCommand> commandType() {
    return AddBookCopyCommand.class;
  }
}
