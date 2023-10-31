package com.pswida.library.catalog.infrastructure.db.document;

import com.pswida.library.catalog.domain.book.BookCategory;
import com.pswida.library.catalog.domain.book.BookDiscussion;
import com.pswida.library.common.domain.DomainEvent;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "books")
@Data
public final class BookDocument {
  @Id
  private String isbn;
  private List<DomainEvent> domainEvents;
  private String title;
  private BookCategory bookCategory;
  private List<String> authors;
  private BookDiscussion discussion;
}

