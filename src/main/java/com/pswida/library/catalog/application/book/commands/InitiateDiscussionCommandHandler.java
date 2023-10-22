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
class InitiateDiscussionCommandHandler extends CommandHandler<InitiateDiscussionCommand> {

  private final ProcessTrackerRepository trackerRepository;

  private final BookRepository bookRepository;

  @Override
  // @Transactional
  protected void doHandle(InitiateDiscussionCommand command) {

    Book book = bookRepository.getByIsbn(command.bookIsbn()).orElseThrow();
    book.initiateDiscussion(command.discussionId());

    TimeConstrainedProcessTracker tracker = trackerRepository.findById(book.toSnapshot().discussion().getTrackerId());
    tracker.endProcess();

    trackerRepository.save(tracker);
    bookRepository.save(book);
  }

  @Override
  protected Class<InitiateDiscussionCommand> commandType() {
    return InitiateDiscussionCommand.class;
  }
}
