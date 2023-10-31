package com.pswida.library.lending.infrastructure.db.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("lending")
@Data
public class LendingDocument {
  @Id
  private String lendingId;
  private String lendedBookCopyId;
  private Instant lendingDate;
  private Instant expectedReturnDate;
}
