package org.boldare.books.application.bookcopy.commands;

import org.boldare.books.application.core.cqs.command.Command;
import org.boldare.books.domain.book.BookIsbn;
import org.boldare.books.domain.bookcopy.BookCopyId;

public record AddBookCopyCommand(BookIsbn bookIsbn, BookCopyId bookCopyId) implements Command {
}
