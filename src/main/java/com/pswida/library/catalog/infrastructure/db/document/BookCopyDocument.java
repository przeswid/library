package com.pswida.library.catalog.infrastructure.db.document;

import com.pswida.library.catalog.domain.bookcopy.BookCopy;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookCopies")
@Data
public class BookCopyDocument {
  @Id
  private String bookCopyId;
  private String bookIsbn;
  private BookCopy.Status status;
}
