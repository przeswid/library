package com.pswida.library.lending.infrastructure.db;

import com.pswida.library.lending.domain.Lending;
import com.pswida.library.lending.domain.LendingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
class LendingRepositoryDb implements LendingRepository {

  private final LendingMapper lendingMapper;

  private final LendingMongoRepository lendingMongoRepository;

  @Override
  public void save(Lending lending) {
    lendingMongoRepository.save(lendingMapper.mapLendingSnapshotToLendingDocument(lending.toSnapshot()));
  }
}
