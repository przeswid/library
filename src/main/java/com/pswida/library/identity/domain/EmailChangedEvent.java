package com.pswida.library.identity.domain;

import com.pswida.library.common.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EmailChangedEvent extends DomainEvent {
  private final UserId userId;
  private final String email;
}
