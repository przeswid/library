package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.application.bookcopy.queries.GetBookCopyByIdResult;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.bookcopy.BookCopy;
import com.pswida.library.catalog.domain.bookcopy.BookCopyId;
import com.pswida.library.catalog.domain.bookcopy.BookCopyQueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
class BookCopyQueryRepositoryImpl implements BookCopyQueryRepository {

  private final BookCopyRepositoryMongoDb springDataRepository;

  private final BookCopyMapper mapper;

  @Override
  public Optional<GetBookCopyByIdResult> getById(BookCopyId bookCopyId) {
    return springDataRepository.findById(bookCopyId.id())
      .map(bc -> GetBookCopyByIdResult.builder()
        .bookCopyId(bc.getBookCopyId())
        .bookIsbn(bc.getBookIsbn())
        .status(bc.getStatus())
        .build());
  }

  @Override
  public List<BookCopy> getByBookIsbn(BookIsbn bookIsbn) {
    return null;
  }

}
