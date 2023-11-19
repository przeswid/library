package com.pswida.library.lending.application;

import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.lending.domain.LendedBookCopyId;
import com.pswida.library.lending.domain.LendingUserId;

public record CreateLendingUserCommand(LendingUserId id, String username) implements Command {
}
