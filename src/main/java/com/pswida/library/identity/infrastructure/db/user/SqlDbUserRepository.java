package com.pswida.library.identity.infrastructure.db.user;

import com.pswida.library.identity.domain.User;
import com.pswida.library.identity.domain.UserId;
import com.pswida.library.identity.domain.UserRepository;
import com.pswida.library.identity.domain.UserSnapshot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
class SqlDbUserRepository implements UserRepository {

  private final UserSpringDataRepository springDataRepository;

  private final UserMapper userMapper;

  @Override
  public void save(User user) {
    UserSnapshot userSnapshot = user.toSnapshot();
    springDataRepository.save(userMapper.mapUserSnapshotToDocument(userSnapshot));
  }

  @Override
  public Optional<User> getUserByUsername(String username) {
    return springDataRepository.getUserByUsername(username)
      .map(userMapper::mapUserDocumentToSnapshot)
      .map(User::fromSnapshot);
  }

  @Override
  public User getUserById(UserId userId) {
    return User.fromSnapshot(userMapper.mapUserDocumentToSnapshot(springDataRepository.getReferenceById(userId.id())));
  }

  @Override
  public UserId nextUserId() {
    return new UserId(UUID.randomUUID().toString());
  }

}
