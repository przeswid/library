package com.pswida.library.lending.domain;

public class LendingUser {

  private final LendingUserId lendingUserId;

  private final String username;

  LendingUser(LendingUserId lendingUserId, String username) {
    this.lendingUserId = lendingUserId;
    this.username = username;
  }

  public static LendingUser createUser(LendingUserId id, String name) {
    return new LendingUser(id, name);
  }

  public LendingUserSnapshot toSnapshot() {
    return new LendingUserSnapshot(lendingUserId, username);
  }

}
