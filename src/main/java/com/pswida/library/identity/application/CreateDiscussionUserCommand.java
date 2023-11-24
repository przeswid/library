package com.pswida.library.identity.application;

import com.pswida.library.common.application.cqs.command.Command;

public record CreateDiscussionUserCommand(String username, String email) implements Command {
}
