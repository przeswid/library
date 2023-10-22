package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.catalog.application.core.cqs.command.CommandHandler;
import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.common.domain.tracker.ProcessTrackerRepository;
import com.pswida.library.common.domain.tracker.TimeConstrainedProcessTracker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class DiscussionProcessStartCommandHandler extends CommandHandler<DiscussionProcessStartCommand> {

  private final ProcessTrackerRepository trackerRepository;

  private final BookRepository bookRepository;

  @Override
  // @Transactional
  protected void doHandle(DiscussionProcessStartCommand command) {

    TimeConstrainedProcessTracker processTracker = TimeConstrainedProcessTracker.startProcess(
      "Book Created: " + command.bookIsbn(), 3);

    Book book = bookRepository.getByIsbn(command.bookIsbn()).orElseThrow();
    book.startDiscussionProcess(processTracker.getProcessTrackerId());

    trackerRepository.save(processTracker);
    bookRepository.save(book);
  }

  @Override
  protected Class<DiscussionProcessStartCommand> commandType() {
    return DiscussionProcessStartCommand.class;
  }
}
