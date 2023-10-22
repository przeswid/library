package com.pswida.library.discussion.domain;

import com.pswida.library.common.domain.DomainEvent;
import com.pswida.library.discussion.domain.event.DiscussionCreated;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Discussion {

  private final DiscussionId id;

  private final DiscussionOwnerId ownerId;

  private final List<Post> posts;
  @Getter
  private final List<DomainEvent> domainEvents;

  public static Discussion newDiscussion(DiscussionOwnerId ownerId) {
    return new Discussion(ownerId, new ArrayList<>(), new ArrayList<>());
  }

  public void restartDiscussion() {
    domainEvents.add(new DiscussionCreated(id, ownerId));
  }

  public void addPost(String content) {
    posts.add(new Post(content));
  }

  Discussion(DiscussionOwnerId ownerId, List<Post> posts, List<DomainEvent> domainEvents) {
    this.ownerId = ownerId;
    this.posts = posts;
    this.domainEvents = domainEvents;
    this.id = new DiscussionId(UUID.randomUUID().toString());

    domainEvents.add(new DiscussionCreated(id, ownerId));
  }

}
