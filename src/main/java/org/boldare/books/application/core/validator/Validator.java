package org.boldare.books.application.core.validator;

import java.util.function.Predicate;

public interface Validator<T> {

  Predicate<T> predicate();

  String message();
}
