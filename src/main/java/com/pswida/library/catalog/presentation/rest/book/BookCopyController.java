package com.pswida.library.catalog.presentation.rest.book;

import com.pswida.library.catalog.application.bookcopy.queries.GetBookCopyByIdQuery;
import com.pswida.library.catalog.application.bookcopy.queries.GetBookCopyByIdResult;
import com.pswida.library.common.application.cqs.query.QueryDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookcopy")
@AllArgsConstructor
class BookCopyController {

  private final QueryDispatcher queryDispatcher;

  @GetMapping(value = "/{bookCopyId}", produces = "application/json")
  ResponseEntity<BookCopyDto> getBookCopyById(@PathVariable String bookCopyId) {
    GetBookCopyByIdQuery getBookCopyByIdQuery = new GetBookCopyByIdQuery(bookCopyId);
    GetBookCopyByIdResult queryResult = queryDispatcher.dispatch(getBookCopyByIdQuery);
    return ResponseEntity.ok(new BookCopyDto(queryResult.bookCopyId(), queryResult.status(), queryResult.bookIsbn()));
  }
}
