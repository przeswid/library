package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.domain.bookcopy.BookCopy;
import com.pswida.library.catalog.domain.bookcopy.BookCopyCommandRepository;
import com.pswida.library.catalog.domain.bookcopy.BookCopyId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
class BookCopyCommandRepositoryImpl implements BookCopyCommandRepository {

  private final BookCopyRepositoryMongoDb springDataRepository;

  private final BookCopyMapper mapper;

  @Override
  public void save(BookCopy bookCopy) {
    springDataRepository.save(mapper.mapBookCopySnapshotToBookCopyDocument(bookCopy.toSnapshot()));
  }

  @Override
  public Optional<BookCopy> getById(BookCopyId bookCopyId) {
    return springDataRepository.findById(bookCopyId.id())
      .map(mapper::mapBookCopyDocumentToBookCopySnapshot)
      .map(BookCopy::fromSnapshot);
  }

}
