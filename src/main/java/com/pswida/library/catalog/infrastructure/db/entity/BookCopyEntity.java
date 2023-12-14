package com.pswida.library.catalog.infrastructure.db.entity;

import com.pswida.library.catalog.domain.bookcopy.BookCopy;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "catalog", name = "book_copies")
@Data
public class BookCopyEntity {
  @Id
  private String bookCopyId;
  private String bookIsbn;
  private BookCopy.Status status;
}
