package com.pswida.library.lending.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookAvailabilityService {

  private final CatalogService catalogService;

  public boolean isAvailable(LendedBookCopyId bookCopyId) {
    return catalogService.getBookToLend(bookCopyId).map(LendedBookWithAvailbility::isAvailable).orElse(false);
  }

}
