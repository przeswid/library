package com.pswida.library.identity.domain;

import com.pswida.library.common.domain.DomainEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
  private final UserId id;
  private final String username;

  private String email;

  private List<DomainEvent> domainEvents;

  public static User newUser(UserId userId, String username, String email) {
    User user = new User(userId, username, email, new ArrayList<>());
    user.addDomainEvent(new UserCreatedEvent(userId, username, email));
    return user;
  }

  public static User fromSnapshot(UserSnapshot userSnapshot) {
    return new User(userSnapshot.id(), userSnapshot.username(), userSnapshot.email(), new ArrayList<>());
  }

  public void updateEmail(String email) {
    if (!EmailValidator.getInstance().isValid(email)) {
      throw new IllegalArgumentException("Email is not valid");
    }
    this.email = email;
    this.addDomainEvent(new EmailChangedEvent(id, this.email));
  }

  public List<DomainEvent> domainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

  public UserSnapshot toSnapshot() {
    return new UserSnapshot(id, username, email);
  }

  private void addDomainEvent(DomainEvent domainEvent) {
    domainEvents.add(domainEvent);
  }

}
