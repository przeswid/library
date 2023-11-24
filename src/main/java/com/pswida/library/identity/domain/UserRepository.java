package com.pswida.library.identity.domain;

import java.util.Optional;

public interface UserRepository {
  void save(User user);

  Optional<User> getUserByUsername(String username);

  User getUserById(UserId id);

  UserId nextUserId();
}
