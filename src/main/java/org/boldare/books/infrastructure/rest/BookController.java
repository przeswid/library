package org.boldare.books.infrastructure.rest;

import lombok.AllArgsConstructor;
import org.boldare.books.application.BookService;
import org.boldare.books.domain.book.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
class BookController {

  private final BookService bookService;

  private final BookDtoMapper bookDtoMapper;

  @GetMapping(value = "/", produces = "application/json")
  ResponseEntity<List<BookDto>> getAllBooks() {
    return ResponseEntity.ok(
      bookService.findAllBooks().stream().map(Book::toSnapshot).map(bookDtoMapper::mapBookSnapshotToBookDto).toList());
  }

}
