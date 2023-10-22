package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.catalog.application.core.cqs.command.Command;
import com.pswida.library.common.domain.tracker.ProcessTrackerId;

public record DiscussionProcessTimedOutCommand(ProcessTrackerId trackerId, boolean retriedMaxTimes) implements Command {
}
