package org.boldare.books;

import org.boldare.books.infrastructure.BookRepositoryInMemory;
import org.boldare.books.model.book.Book;
import org.boldare.books.model.book.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

  private final BookRepository repository = BookRepositoryInMemory.INSTANCE;

  public App() {
    add10FamousBooks();
  }

  public static void main(String[] args) {
    App app = new App();
    app.showAll();
    app.findBookByTitle().ifPresent(System.out::println);
  }

  public Optional<Book> findBookByTitle() {
    System.out.println("Enter title of book to find:");
    String title = readFromConsoleInput();
    return repository.getByTitle(title);
  }

  public void showAll() {
    repository.getAll().forEach(System.out::println);
  }

  private void add10FamousBooks() {
    repository.add(new Book("The Lord of the Rings", "978-0261103252", List.of("J.R.R. Tolkien")));
    repository.add(new Book("Le Petit Prince", "978-2070612758", List.of("Antoine de Saint-Exupéry")));
    repository.add(new Book("Harry Potter and the Philosopher's Stone", "978-0747532743", List.of("J.K. Rowling")));
    repository.add(new Book("And Then There Were None", "978-0312330873", List.of("Agatha Christie")));
    repository.add(new Book("Dream of the Red Chamber", "978-0140443714", List.of("Cao Xueqin")));
    repository.add(new Book("The Hobbit", "978-0261103283", List.of("J.R.R. Tolkien")));
    repository.add(new Book("She: A History of Adventure", "978-0199536425", List.of("H. Rider Haggard")));
    repository.add(new Book("The Lion, the Witch and the Wardrobe", "978-0006716631", List.of("C.S. Lewis")));
    repository.add(new Book("The Da Vinci Code", "978-0307474278", List.of("Dan Brown")));
    repository.add(new Book("The Catcher in the Rye", "978-0316769488", List.of("J.D. Salinger")));
  }

  private String readFromConsoleInput() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
