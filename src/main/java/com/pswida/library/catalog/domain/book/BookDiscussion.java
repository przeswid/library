package com.pswida.library.catalog.domain.book;

import com.pswida.library.common.domain.tracker.ProcessTrackerId;
import com.pswida.library.discussion.domain.DiscussionId;
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
    INITIALIZED, REQUESTED, READY, FAILED
  }

  public static BookDiscussion initializeDiscussion() {
    return new BookDiscussion(Status.INITIALIZED, null, null);
  }

  public BookDiscussion nowRequested(ProcessTrackerId trackerId) {
    if (status != Status.INITIALIZED)
      throw new IllegalStateException("Discussion is not requested");
    return new BookDiscussion(Status.REQUESTED, trackerId, null);
  }

  public BookDiscussion nowReady(DiscussionId discussionId) {
    if (status != Status.REQUESTED)
      throw new IllegalStateException("Discussion is not started");
    return new BookDiscussion(Status.READY, trackerId, discussionId);
  }

  public BookDiscussion nowFailed() {
    if (status != Status.REQUESTED)
      throw new IllegalStateException("Discussion is not started");
    return new BookDiscussion(Status.FAILED, trackerId, discussionId);
  }
}
