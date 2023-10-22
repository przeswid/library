package com.pswida.library.catalog.domain.book;

import com.pswida.library.catalog.domain.book.event.BookCreated;
import com.pswida.library.common.domain.DomainEvent;
import com.pswida.library.common.domain.tracker.ProcessTrackerId;
import com.pswida.library.discussion.domain.DiscussionId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public final class Book {

  @EqualsAndHashCode.Include
  private final BookIsbn isbn;
  @Getter
  private final List<DomainEvent> domainEvents;
  private String title;
  private BookCategory bookCategory;
  private List<String> authors;
  private BookDiscussion discussion;

  Book(String title, BookIsbn isbn, List<String> authors, BookCategory bookCategory, BookDiscussion discussion) {
    validateTitle(title);
    validateAuthors(authors);
    validateIsbn(isbn);

    this.isbn = isbn;
    this.authors = authors;
    this.title = title;
    this.bookCategory = bookCategory;
    this.domainEvents = new ArrayList<>();
    this.discussion = discussion;
  }

  public static Book fromSnapshot(BookSnapshot snapshot) {
    return new Book(snapshot.title(), snapshot.isbn(), snapshot.authors(), snapshot.bookCategory(),
      snapshot.discussion());
  }

  public static Book newBook(String title, BookIsbn isbn, List<String> authors, BookCategory bookCategory) {
    Book book = new Book(title, isbn, authors, bookCategory, BookDiscussion.initializeDiscussion());
    registerBookCreatedEvent(book);
    return book;
  }

  public BookSnapshot toSnapshot() {
    return new BookSnapshot(isbn, title, authors, bookCategory, discussion);
  }

  public void initiateDiscussion(DiscussionId discussionId) {
    this.discussion = this.discussion.nowReady(discussionId);
  }

  public void startDiscussionProcess(ProcessTrackerId trackerId) {
    this.discussion = this.discussion.nowRequested(trackerId);
  }

  public void failDiscussionProcess() {
    this.discussion = this.discussion.nowFailed();
  }

  public void retryDiscussionProcess() {
    registerBookCreatedEvent(this);
  }

  private static void registerBookCreatedEvent(Book book) {
    book.domainEvents.add(new BookCreated(book.isbn));
  }

  private void validateIsbn(BookIsbn bookIsbn) {
    if (bookIsbn == null || bookIsbn.isbn().isBlank()) {
      throw new IllegalArgumentException("ISBN cannot be blank");
    }
  }

  private void validateTitle(String title) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be blank");
    }
  }

  private void validateAuthors(List<String> authors) {
    if (authors == null || authors.isEmpty()) {
      throw new IllegalArgumentException("Author cannot be blank");
    } else {
      authors.forEach(a -> {
        if (a == null || a.isBlank()) {
          throw new IllegalArgumentException("Author cannot be blank");
        }
      });
    }
  }
}

