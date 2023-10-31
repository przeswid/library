package com.pswida.library.catalog.application.book.queries;

import com.pswida.library.common.application.cqs.query.QueryHandler;
import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookDiscussion;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.discussion.domain.DiscussionId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class FindBookByTitleQueryHandler extends QueryHandler<FindBookByTtitleQuery, FindBookByTtitleQueryResult> {

  private final BookRepository bookRepository;

  @Override
  protected FindBookByTtitleQueryResult doHandle(FindBookByTtitleQuery query) {
    return bookRepository.getByTitle(query.bookTitle())
      .map(Book::toSnapshot)
      .map(book -> new FindBookByTtitleQueryResult(book.getIsbn(), book.getTitle(),
        Optional.ofNullable(book.getDiscussion()).map(BookDiscussion::getDiscussionId).map(DiscussionId::id).orElse(null),
        book.getDiscussion().getStatus()))
      .orElseThrow(() -> new RuntimeException("Book with title: " + query.bookTitle() + " not found"));
  }

  @Override
  protected Class<FindBookByTtitleQuery> queryType() {
    return FindBookByTtitleQuery.class;
  }
}
