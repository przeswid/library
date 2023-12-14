package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.catalog.domain.book.BookSnapshot;
import com.pswida.library.common.application.cqs.command.Command;

public record AddBookCommand(BookSnapshot book) implements Command {
}
