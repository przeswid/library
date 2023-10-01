package org.boldare.books.domain.book.location;

public class Shelf extends BookLocation {
  private final String shelfNumber;

  public Shelf(String shelfNumber) {
    this.shelfNumber = shelfNumber;
  }
}
