package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.tracker.domain.ProcessTracker;
import com.pswida.library.tracker.domain.ProcessTrackerId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
class BookRepositoryImpl implements BookRepository {

  private final BookRepositoryMongoDb mongoRepository;

  private final BookMapper mapper;

  @Override
  public void save(Book book) {
    mongoRepository.save(mapper.mapBookSnapshotToBookDocument(book.toSnapshot()));
  }

  @Override
  public Optional<Book> getByTitle(String title) {
    return mongoRepository.getByTitle(title)
      .stream()
      .findFirst()
      .map(mapper::mapBookDocumentToBookSnapshot)
      .map(Book::fromSnapshot);
  }

  @Override
  public Optional<Book> getByIsbn(BookIsbn isbn) {
    return mongoRepository.findById(isbn.isbn()).map(mapper::mapBookDocumentToBookSnapshot).map(Book::fromSnapshot);
  }

  @Override
  public Optional<Book> getByTrackerId(ProcessTrackerId trackerId) {
//    return mongoRepository.findByDiscussion_TrackerIdAndDiscussion_Status(trackerId, ProcessTracker.Status.STARTED)
//      .map(mapper::mapBookDocumentToBookSnapshot)
//      .map(Book::fromSnapshot);
    return Optional.empty();
  }

  @Override
  public Collection<Book> getAll() {
    return mongoRepository.findAll()
      .stream()
      .map(mapper::mapBookDocumentToBookSnapshot)
      .map(Book::fromSnapshot)
      .toList();
  }
}
