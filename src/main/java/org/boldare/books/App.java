package org.boldare.books;

import org.boldare.books.commons.DateTimeProvider;
import org.boldare.books.commons.RealDateTimeProvider;
import org.boldare.books.infrastructure.BookRepositoryInMemory;
import org.boldare.books.model.book.Book;
import org.boldare.books.model.book.BookRepository;

import java.util.List;
import java.util.Scanner;

public class App {

  private final BookRepository bookRepository = BookRepositoryInMemory.INSTANCE;

  private final DateTimeProvider realDateTimeProvider = new RealDateTimeProvider();

  public App() {
    add10FamousBooks();
  }

  public static void main(String[] args) {
    App app = new App();
    app.showAll();
    app.findBookByTitle().forEach(System.out::println);
  }

  public List<Book> findBookByTitle() {
    System.out.println("Enter title of book to find:");
    String title = readFromConsoleInput();
    return bookRepository.searchByTitle(title);
  }

  public void rentBookCopy(String title, String bookCopyId, String clientIdentifier) {
    Book book = bookRepository.getByTitle(title)
      .orElseThrow(() -> new IllegalStateException("Book not found"));

    Book.RentDataDto rentData = new Book.RentDataDto(bookCopyId, realDateTimeProvider.getCurrentDateTime(), clientIdentifier);
    book.rentBookCopy(rentData);
  }

  public void showAll() {
    bookRepository.getAll().forEach(System.out::println);
  }

  private void add10FamousBooks() {
    addBookWithTwoCopies(new Book("The Lord of the Rings", "978-0261103252", List.of("J.R.R. Tolkien")));
    addBookWithTwoCopies(new Book("Le Petit Prince", "978-2070612758", List.of("Antoine de Saint-Exupéry")));
    addBookWithTwoCopies(
      new Book("Harry Potter and the Philosopher's Stone", "978-0747532743", List.of("J.K. Rowling")));
    addBookWithTwoCopies(new Book("And Then There Were None", "978-0312330873", List.of("Agatha Christie")));
    addBookWithTwoCopies(new Book("Dream of the Red Chamber", "978-0140443714", List.of("Cao Xueqin")));
    addBookWithTwoCopies(new Book("The Hobbit", "978-0261103283", List.of("J.R.R. Tolkien")));
    addBookWithTwoCopies(new Book("She: A History of Adventure", "978-0199536425", List.of("H. Rider Haggard")));
    addBookWithTwoCopies(new Book("The Lion, the Witch and the Wardrobe", "978-0006716631", List.of("C.S. Lewis")));
    addBookWithTwoCopies(new Book("The Da Vinci Code", "978-0307474278", List.of("Dan Brown")));
    addBookWithTwoCopies(new Book("The Catcher in the Rye", "978-0316769488", List.of("J.D. Salinger")));
  }

  private void addBookWithTwoCopies(Book book) {
    book.addBookCopy(book.getIsbn() + "-1");
    book.addBookCopy(book.getIsbn() + "-2");
    bookRepository.add(book);
  }

  private String readFromConsoleInput() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
