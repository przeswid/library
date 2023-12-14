package com.pswida.library.identity.application;

import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.identity.domain.UserId;

public record ChangeUserEmailCommand(UserId userId, String email) implements Command {
}
