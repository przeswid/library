package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.tracker.domain.ProcessTrackerRepository;
import com.pswida.library.tracker.domain.ProcessTracker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
@Log4j2
class DiscussionProcessStartCommandHandler extends CommandHandler<DiscussionProcessStartCommand> {

  private final ProcessTrackerRepository trackerRepository;

  private final BookRepository bookRepository;

  @Override
  // @Transactional
  protected void doHandle(DiscussionProcessStartCommand command) {

    log.info("Handling DiscussionProcessStartCommand: {}", command.bookIsbn());

    Book book = bookRepository.getByIsbn(command.bookIsbn()).orElseThrow();
    if (book.toSnapshot().getDiscussion().isAlreadyTracked()) {
      return;
    }

    ProcessTracker processTracker = ProcessTracker.startProcess(
      "Book Created: " + command.bookIsbn(), 3, Instant.now().plusSeconds(10));
    book.trackDiscussion(processTracker.getProcessTrackerId());

    trackerRepository.save(processTracker);
    bookRepository.save(book);
  }

  @Override
  protected Class<DiscussionProcessStartCommand> commandType() {
    return DiscussionProcessStartCommand.class;
  }
}
