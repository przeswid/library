package com.pswida.library.lending.infrastructure.services;

import com.pswida.library.lending.domain.CatalogService;
import com.pswida.library.lending.domain.LendedBookCopyId;
import com.pswida.library.lending.domain.LendedBookWithAvailbility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class CatalogServiceImpl implements CatalogService {

  private final CatalogServiceAdapter catalogServiceAdapter;
  @Override
  public Optional<LendedBookWithAvailbility> getBookToLend(LendedBookCopyId bookCopyId) {
    return catalogServiceAdapter.getLendedBookCopy(bookCopyId.id());
  }
}
