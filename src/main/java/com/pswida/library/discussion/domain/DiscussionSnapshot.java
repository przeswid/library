package com.pswida.library.discussion.domain;

import java.util.List;

public record DiscussionSnapshot(DiscussionId id, DiscussionOwnerId ownerId, List<Post> posts) {
}
