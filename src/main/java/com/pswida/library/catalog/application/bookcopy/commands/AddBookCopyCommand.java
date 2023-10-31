package com.pswida.library.catalog.application.bookcopy.commands;

import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.bookcopy.BookCopyId;

public record AddBookCopyCommand(BookIsbn bookIsbn, BookCopyId bookCopyId) implements Command {
}
