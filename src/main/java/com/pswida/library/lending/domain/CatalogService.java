package com.pswida.library.lending.domain;

import java.util.Optional;

public interface CatalogService {
  Optional<LendedBookWithAvailbility> getBookToLend(LendedBookCopyId bookCopyId);
}
