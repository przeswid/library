package com.pswida.library.discussion.application;

import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.discussion.domain.user.UserId;

public record UpdateUserEmailCommand(UserId userId, String email) implements Command {
}
