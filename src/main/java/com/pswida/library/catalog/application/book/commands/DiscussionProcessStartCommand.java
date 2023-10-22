package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.catalog.application.core.cqs.command.Command;
import com.pswida.library.catalog.domain.book.BookIsbn;

public record DiscussionProcessStartCommand(BookIsbn bookIsbn) implements Command {
}
