package com.pswida.library.discussion.domain;

import java.util.Optional;

public interface DiscussionRepository {

  Optional<Discussion> getByOwnerId(DiscussionOwnerId id);

  void save(Discussion discussion);
}
