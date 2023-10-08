package org.boldare.books.application.bookcopy.commands;

import org.boldare.books.application.core.cqs.command.Command;
import org.boldare.books.domain.bookcopy.BookCopyId;

public record BorrowBookCopyCommand(BookCopyId bookCopyId) implements Command {
}
