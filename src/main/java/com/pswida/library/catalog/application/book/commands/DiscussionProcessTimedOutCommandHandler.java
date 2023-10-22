package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.catalog.application.core.cqs.command.CommandHandler;
import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class DiscussionProcessTimedOutCommandHandler extends CommandHandler<DiscussionProcessTimedOutCommand> {

  private final ApplicationEventPublisher eventPublisher;

  private final BookRepository bookRepository;

  @Override
  protected void doHandle(DiscussionProcessTimedOutCommand command) {
    Book book = bookRepository.getByTrackerId(command.trackerId()).orElseThrow();

    if (command.retriedMaxTimes()) {
      book.failDiscussionProcess();
    } else {
      book.retryDiscussionProcess();
    }

    bookRepository.save(book);
    book.getDomainEvents().forEach(eventPublisher::publishEvent);
  }

  @Override
  protected Class<DiscussionProcessTimedOutCommand> commandType() {
    return DiscussionProcessTimedOutCommand.class;
  }
}
