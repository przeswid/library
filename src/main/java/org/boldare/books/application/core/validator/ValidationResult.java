package org.boldare.books.application.core.validator;

import java.util.List;

public record ValidationResult(Status status, List<ValidationError> errors) {
  public enum Status {
    SUCCESS,
    FAILURE
  }
}
