package com.pswida.library.identity.infrastructure.db.user;

import com.pswida.library.identity.domain.UserId;
import com.pswida.library.identity.domain.UserSnapshot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface UserMapper {
  UserDocument mapUserSnapshotToDocument(UserSnapshot snapshot);

  UserSnapshot mapUserDocumentToSnapshot(UserDocument document);

  default String mapUserIdToId(UserId userId) {
    return userId.id();
  }

  default UserId mapIdTuUserId(String id) {
    return new UserId(id);
  }
}

