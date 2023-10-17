package com.pswida.library.catalog.application.bookcopy.commands;

import com.pswida.library.catalog.domain.bookcopy.BookCopyId;
import com.pswida.library.catalog.application.core.cqs.command.Command;

public record BorrowBookCopyCommand(BookCopyId bookCopyId) implements Command {
}
