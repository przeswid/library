package com.pswida.library.catalog.infrastructure.db.entity;

import com.pswida.library.catalog.domain.book.BookCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(schema = "catalog", name = "books")
@Data
public final class BookEntity {
  @Id
  private String isbn;
  private String title;
  private BookCategory bookCategory;
  private List<String> authors;
//  private BookDiscussion discussion;
}

