package com.pswida.library.lending.infrastructure.db;

import com.pswida.library.lending.domain.LendingUserId;
import com.pswida.library.lending.domain.LendingUserSnapshot;
import com.pswida.library.lending.infrastructure.db.entity.LendingUserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface LendingUserMapper {

  LendingUserEntity mapLendingUserSnapshotToEntity(LendingUserSnapshot lendingUserSnapshot);

  default Long mapLendingIdToId(LendingUserId lendingUserId) {
    return lendingUserId.id();
  }
}
