package com.pswida.library.catalog.domain.book;

import com.pswida.library.discussion.domain.DiscussionId;
import com.pswida.library.tracker.domain.ProcessTrackerId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDiscussion {
  Status status;
  ProcessTrackerId trackerId;
  DiscussionId discussionId;

  public enum Status {
    REQUESTED, READY, FAILED
  }

  public static BookDiscussion initializeDiscussion() {
    return new BookDiscussion(Status.REQUESTED, null, null);
  }

  public BookDiscussion nowTracked(ProcessTrackerId trackerId) {
    if (status != Status.REQUESTED) {
      throw new IllegalStateException("Discussion is not requested");
    }
    return new BookDiscussion(Status.REQUESTED, trackerId, null);
  }

  public BookDiscussion nowReady(DiscussionId discussionId) {
    if (status != Status.REQUESTED) {
      throw new IllegalStateException("Discussion is not started");
    }
    return new BookDiscussion(Status.READY, trackerId, discussionId);
  }

  public BookDiscussion nowFailed() {
    if (status != Status.REQUESTED) {
      throw new IllegalStateException("Discussion is not started");
    }
    return new BookDiscussion(Status.FAILED, trackerId, discussionId);
  }

  public boolean isAlreadyTracked() {
    return trackerId != null;
  }
}
