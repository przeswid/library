package org.boldare.books.application.book.queries;

import lombok.AllArgsConstructor;
import org.boldare.books.application.core.cqs.query.QueryHandler;
import org.boldare.books.domain.book.Book;
import org.boldare.books.domain.book.BookRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class FindBookByTitleQueryHandler extends QueryHandler<FindBookByTtitleQuery, FindBookByTtitleQueryResult> {

  private final BookRepository bookRepository;

  @Override
  protected FindBookByTtitleQueryResult doHandle(FindBookByTtitleQuery query) {
    return bookRepository.getByTitle(query.bookTitle())
      .map(Book::toSnapshot)
      .map(book -> new FindBookByTtitleQueryResult(book.isbn(), book.title()))
      .orElseThrow(() -> new RuntimeException("Book with title: " + query.bookTitle() + " not found"));
  }

  @Override
  protected Class<FindBookByTtitleQuery> queryType() {
    return FindBookByTtitleQuery.class;
  }
}
