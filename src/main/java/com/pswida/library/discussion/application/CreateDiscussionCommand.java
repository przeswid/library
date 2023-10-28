package com.pswida.library.discussion.application;

import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.discussion.domain.DiscussionOwnerId;

public record CreateDiscussionCommand(DiscussionOwnerId ownerId) implements Command {
}
