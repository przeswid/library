package org.boldare.books.application.core.validator;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseValidatorService<T> {

  public ValidationResult validate(T object) {
    List<ValidationError> errors = new ArrayList<>();

    for (Validator<T> validator : validators()) {
      boolean isFulfilled = validator.predicate().test(object);

      if (!isFulfilled) {
        errors.add(new ValidationError(validator.message()));
      }
    }

    if (!errors.isEmpty()) {
      return new ValidationResult(ValidationResult.Status.FAILURE, errors);
    } else {
      return new ValidationResult(ValidationResult.Status.SUCCESS, List.of());
    }
  }

  protected abstract List<Validator<T>> validators();
}
