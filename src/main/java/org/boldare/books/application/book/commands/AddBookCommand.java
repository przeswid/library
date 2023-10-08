package org.boldare.books.application.book.commands;

import org.boldare.books.application.core.cqs.command.Command;
import org.boldare.books.domain.book.BookSnapshot;

public record AddBookCommand(BookSnapshot book) implements Command {
}
