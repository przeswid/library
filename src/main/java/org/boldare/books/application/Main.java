package org.boldare.books.application;

import lombok.Builder;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {

    List<Room> rooms = List.of(Room.builder().type("SINGLE").occupancy(1).build(),
      Room.builder().type("DOUBLE").occupancy(2).build(), Room.builder().type("DOUBLE").occupancy(5).build(),
      Room.builder().type("TRIPLE").occupancy(0).build());

    rooms.stream().sorted(Comparator.comparing(item -> item.occupancy())).forEach(System.out::println);
  }
}

@Builder
record Room(int occupancy, String type) {
}

