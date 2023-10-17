package com.pswida.library.catalog.application.book.queries;

import com.pswida.library.catalog.application.core.cqs.query.QueryHandler;
import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookRepository;
import lombok.AllArgsConstructor;
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
