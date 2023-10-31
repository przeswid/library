package com.pswida.library.common.application.validator;

import java.util.function.Predicate;

public interface Validator<T> {

  Predicate<T> predicate();

  String message();
}
