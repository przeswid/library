package com.pswida.library.identity.infrastructure.db.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserSpringDataRepository extends JpaRepository<UserDocument, String> {
  Optional<UserDocument> getUserByUsername(String username);
}
