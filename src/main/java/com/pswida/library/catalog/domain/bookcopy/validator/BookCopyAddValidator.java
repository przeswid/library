package com.pswida.library.catalog.domain.bookcopy.validator;

import com.pswida.library.catalog.application.core.validator.BaseValidatorService;
import com.pswida.library.catalog.application.core.validator.Validator;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.catalog.domain.bookcopy.BookCopySnapshot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class BookCopyAddValidator extends BaseValidatorService<BookCopySnapshot> {

  // Is it ok that this validator is using BookRepository?
  private final BookRepository bookRepository;

  private final Validator<BookCopySnapshot> validateBookIsbn = new Validator<>() {
    @Override
    public Predicate<BookCopySnapshot> predicate() {
      return bc -> bookRepository.getByIsbn(bc.bookIsbn()).isPresent();
    }

    @Override
    public String message() {
      return "There is no book with given ISBN.";
    }
  };

  @Override
  protected List<Validator<BookCopySnapshot>> validators() {
    return validators;
  }

  private final List<Validator<BookCopySnapshot>> validators = List.of(validateBookIsbn);

}
