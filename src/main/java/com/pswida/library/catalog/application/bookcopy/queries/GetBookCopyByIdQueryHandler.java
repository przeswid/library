package com.pswida.library.catalog.application.bookcopy.queries;

import com.pswida.library.catalog.domain.bookcopy.BookCopyId;
import com.pswida.library.catalog.domain.bookcopy.BookCopyQueryRepository;
import com.pswida.library.common.application.cqs.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class GetBookCopyByIdQueryHandler extends QueryHandler<GetBookCopyByIdQuery, GetBookCopyByIdResult> {

  private final BookCopyQueryRepository queryRepository;

  @Override
  protected GetBookCopyByIdResult doHandle(GetBookCopyByIdQuery query) {
    return queryRepository.getById(new BookCopyId(query.bookCopyId())).orElseThrow();
  }

  @Override
  protected Class<GetBookCopyByIdQuery> queryType() {
    return GetBookCopyByIdQuery.class;
  }
}
