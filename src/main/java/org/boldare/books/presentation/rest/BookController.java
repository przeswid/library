package org.boldare.books.presentation.rest;

import lombok.AllArgsConstructor;
import org.boldare.books.application.book.queries.FindBookByTtitleQuery;
import org.boldare.books.application.core.cqs.query.QueryDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
class BookController {

  private final QueryDispatcher queryDispatcher;

  private final BookDtoMapper bookDtoMapper;

  @GetMapping(value = "/", produces = "application/json")
  ResponseEntity<BookDto> getBookByTitle(@RequestParam String title) {

    FindBookByTtitleQuery query = new FindBookByTtitleQuery(title);
    return ResponseEntity.ok(bookDtoMapper.mapBookResultToBookDto(queryDispatcher.dispatch(query)));
  }

}
