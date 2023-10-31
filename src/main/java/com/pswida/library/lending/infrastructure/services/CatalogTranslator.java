package com.pswida.library.lending.infrastructure.services;

import com.pswida.library.lending.domain.LendedBookWithAvailbility;
import org.springframework.stereotype.Component;

@Component
class CatalogTranslator {

  LendedBookWithAvailbility toLendedBookWithAvailbility(BookCopyDto bookCopy) {
    return new LendedBookWithAvailbility(bookCopy.bookCopyId(), bookCopy.status() == BookCopyStatus.AVAILABLE);
  }

}
