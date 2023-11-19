package com.pswida.library.lending.infrastructure.db;

import com.pswida.library.lending.domain.LendingUser;
import com.pswida.library.lending.domain.LendingUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
class LendingUserRepositoryDb implements LendingUserRepository {

  private final LendingUserMapper lendingUserMapper;

  private final LendingUserSqlRepository lendingUserSqlRepository;

  @Override
  public void save(LendingUser lending) {
    lendingUserSqlRepository.save(lendingUserMapper.mapLendingUserSnapshotToEntity(lending.toSnapshot()));
  }

}
