package com.pswida.library.lending.presentation;

import com.pswida.library.common.application.cqs.command.CommandDispatcher;
import com.pswida.library.lending.application.LendABookCommand;
import com.pswida.library.lending.domain.LendedBookCopyId;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lending")
@AllArgsConstructor
@Log4j2
class LendingController {

  private final CommandDispatcher commandDispatcher;

  @PostMapping(value = "/{lendedBookCopyId}")
  ResponseEntity<String> lendBookCopy(@PathVariable String lendedBookCopyId) {
    log.info("Lending book copy with id: {}", lendedBookCopyId);

    LendABookCommand command = new LendABookCommand(new LendedBookCopyId(lendedBookCopyId));
    commandDispatcher.dispatch(command);

    return ResponseEntity.ok(lendedBookCopyId);
  }
}
