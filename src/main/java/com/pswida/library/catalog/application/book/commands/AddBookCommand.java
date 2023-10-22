package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.catalog.application.core.cqs.command.Command;
import com.pswida.library.catalog.domain.book.BookSnapshot;

public record AddBookCommand(BookSnapshot book) implements Command {
}
