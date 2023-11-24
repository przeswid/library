package com.pswida.library.discussion.application;

import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.discussion.domain.DiscussionOwnerId;
import com.pswida.library.discussion.domain.user.UserId;

public record CreateUserCommand(UserId userId, String username, String email) implements Command {
}
