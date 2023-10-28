package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.discussion.domain.DiscussionId;

public record InitiateDiscussionCommand(BookIsbn bookIsbn, DiscussionId discussionId) implements Command {
}
