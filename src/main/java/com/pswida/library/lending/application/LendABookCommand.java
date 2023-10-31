package com.pswida.library.lending.application;

import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.lending.domain.LendedBookCopyId;

public record LendABookCommand(LendedBookCopyId bookCopyId) implements Command {
}
