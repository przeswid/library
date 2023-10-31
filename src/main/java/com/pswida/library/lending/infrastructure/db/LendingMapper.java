package com.pswida.library.lending.infrastructure.db;

import com.pswida.library.lending.domain.LendedBookCopyId;
import com.pswida.library.lending.domain.LendingId;
import com.pswida.library.lending.domain.LendingSnapshot;
import com.pswida.library.lending.infrastructure.db.document.LendingDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface LendingMapper {
  LendingDocument mapLendingSnapshotToLendingDocument(LendingSnapshot lendingSnapshot);

  default String mapLendingId(LendingId lendingId) {
    return lendingId.id();
  }

  default String mapLendedBookId(LendedBookCopyId lendedBookCopyId) {
    return lendedBookCopyId.id();
  }
}
