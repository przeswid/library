package com.pswida.library.catalog.presentation.rest.book;

import com.pswida.library.catalog.application.book.commands.AddBookCommand;
import com.pswida.library.catalog.application.book.queries.FindBookByTtitleQuery;
import com.pswida.library.common.application.cqs.command.CommandDispatcher;
import com.pswida.library.common.application.cqs.query.QueryDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
class BookController {

  private final QueryDispatcher queryDispatcher;

  private final CommandDispatcher commandDispatcher;

  private final BookDtoMapper bookDtoMapper;

  @GetMapping(produces = "application/json")
  ResponseEntity<BookDto> getBookByTitle(@RequestParam String title) {

    FindBookByTtitleQuery query = new FindBookByTtitleQuery(title);

    return ResponseEntity.ok(bookDtoMapper.mapBookResultToBookDto(queryDispatcher.dispatch(query)));
  }

  @PostMapping(value = "/", consumes = "application/json")
  ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
    AddBookCommand command = new AddBookCommand(bookDtoMapper.mapBookDtoToBookSnapshot(bookDto));
    commandDispatcher.dispatch(command);
    return ResponseEntity.ok(bookDto.isbn());
  }

}
