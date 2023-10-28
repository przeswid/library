package com.pswida.library.common.application.validator;

import java.util.List;

public class ValidationException extends RuntimeException {

  private final List<ValidationError> validationErrors;

  public ValidationException(List<ValidationError> validationErrors) {
    super("Validation exception");
    this.validationErrors = validationErrors;
  }

  public List<ValidationError> getValidationErrors() {
    return validationErrors;
  }
}
