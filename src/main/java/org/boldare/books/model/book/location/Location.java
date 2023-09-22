package org.boldare.books.model.book.location;

public abstract sealed class Location permits Renter, Shelf, Desk {
  private final String name;

  protected Location(String name) {
    this.name = name;
  }

  public String getName() { return name; }
}
