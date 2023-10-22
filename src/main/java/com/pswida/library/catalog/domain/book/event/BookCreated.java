package com.pswida.library.catalog.domain.book.event;

import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.common.domain.DomainEvent;
import lombok.Value;

@Value
public class BookCreated extends DomainEvent {

  BookIsbn bookIsbn;
}
