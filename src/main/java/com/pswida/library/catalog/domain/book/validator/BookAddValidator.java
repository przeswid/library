package com.pswida.library.catalog.domain.book.validator;

import com.pswida.library.catalog.application.core.validator.BaseValidatorService;
import com.pswida.library.catalog.application.core.validator.Validator;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.catalog.domain.book.BookSnapshot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class BookAddValidator extends BaseValidatorService<BookSnapshot> {

  private final BookRepository bookRepository;

  private final Validator<BookSnapshot> validateIsbn = new Validator<>() {
    @Override
    public Predicate<BookSnapshot> predicate() {
      return book -> bookRepository.getByIsbn(book.isbn()).isEmpty();
    }

    @Override
    public String message() {
      return "There is already a book with this ISBN.";
    }
  };

  @Override
  protected List<Validator<BookSnapshot>> validators() {
    return validators;
  }

  private final List<Validator<BookSnapshot>> validators = List.of(validateIsbn);

}
