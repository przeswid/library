package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.common.application.cqs.command.CommandHandler;
import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.tracker.domain.ProcessTrackerRepository;
import com.pswida.library.tracker.domain.ProcessTracker;
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
    book.confirmDiscussion(command.discussionId());

    ProcessTracker tracker = trackerRepository.findById(book.toSnapshot().getDiscussion().getTrackerId());
    tracker.endProcess();

    trackerRepository.save(tracker);
    bookRepository.save(book);
  }

  @Override
  protected Class<InitiateDiscussionCommand> commandType() {
    return InitiateDiscussionCommand.class;
  }
}
