package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class DiscussionProcessTimedOutCommandHandler extends CommandHandler<DiscussionProcessTimedOutCommand> {

  private final ApplicationEventPublisher eventPublisher;

  private final BookRepository bookRepository;

  @Override
  protected void doHandle(DiscussionProcessTimedOutCommand command) {
    Optional<Book> byTrackerId = bookRepository.getByTrackerId(command.trackerId());

    Book book = byTrackerId.orElseThrow();

    if (command.retriedMaxTimes()) {
      book.failDiscussion();
    } else {
      book.retryDiscussionRequest();
    }

    bookRepository.save(book);
    book.getDomainEvents().forEach(eventPublisher::publishEvent);
  }

  @Override
  protected Class<DiscussionProcessTimedOutCommand> commandType() {
    return DiscussionProcessTimedOutCommand.class;
  }
}
