package com.pswida.library.discussion.domain.event;

import com.pswida.library.common.domain.DomainEvent;
import com.pswida.library.discussion.domain.DiscussionId;
import com.pswida.library.discussion.domain.DiscussionOwnerId;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class DiscussionCreated extends DomainEvent {
  DiscussionId discussionId;
  DiscussionOwnerId ownerId;
}
