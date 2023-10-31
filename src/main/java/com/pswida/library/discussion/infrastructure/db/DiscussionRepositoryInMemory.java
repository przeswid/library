package com.pswida.library.discussion.infrastructure.db;

import com.pswida.library.discussion.domain.Discussion;
import com.pswida.library.discussion.domain.DiscussionOwnerId;
import com.pswida.library.discussion.domain.DiscussionRepository;
import com.pswida.library.discussion.domain.DiscussionSnapshot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
class DiscussionRepositoryInMemory implements DiscussionRepository {

  private final Map<String, Discussion> discussions = new HashMap<>();

  @Override
  public Optional<Discussion> getByOwnerId(DiscussionOwnerId id) {
    return discussions.entrySet()
      .stream()
      .map(Map.Entry::getValue)
      .map(Discussion::getSnapshot)
      .filter(d -> d.ownerId().equals(id))
      .findFirst()
      .map(Discussion::fromSnapshot);
  }

  @Override
  public void save(Discussion discussion) {
    DiscussionSnapshot snapshot = discussion.getSnapshot();
    discussions.put(snapshot.id().id(), discussion);
  }
}
