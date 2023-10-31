package com.pswida.library.catalog.application.book.commands;

import com.pswida.library.common.application.cqs.command.Command;
import com.pswida.library.tracker.domain.ProcessTrackerId;

public record DiscussionProcessTimedOutCommand(ProcessTrackerId trackerId, boolean retriedMaxTimes) implements Command {
}
