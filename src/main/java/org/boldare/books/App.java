package org.boldare.books;

import org.boldare.books.application.BookService;
import org.boldare.books.domain.book.Book;
import org.boldare.books.domain.book.BookCategory;
import org.boldare.books.domain.book.BookRepository;
import org.boldare.books.infrastructure.BookRepositoryInMemory;

import java.util.List;
import java.util.Scanner;

public class App {

  private final BookRepository bookRepository = BookRepositoryInMemory.INSTANCE;
  private final BookService bookService = new BookService(bookRepository);

  public App() {
    add10FamousBooks();
  }

  public static void main(String[] args) {
    App app = new App();
    app.showAll();
    app.findBookByTitle();
  }

  private void findBookByTitle() {
    System.out.println("Enter title of book to find:");
    String title = readFromConsoleInput();
    bookService.findBookByTitle(title).forEach(System.out::println);
  }

  private void showAll() {
    System.out.println("All books:");
    bookService.findAllBooks().forEach(System.out::println);
  }

  private void add10FamousBooks() {
    addBookWithTwoCopies(new Book("The Lord of the Rings", "978-0261103252", List.of("J.R.R. Tolkien"), BookCategory.NOVEL));
    addBookWithTwoCopies(new Book("Le Petit Prince", "978-2070612758", List.of("Antoine de Saint-Exupéry"), BookCategory.NOVEL));
    addBookWithTwoCopies(
      new Book("Harry Potter and the Philosopher's Stone", "978-0747532743", List.of("J.K. Rowling"), BookCategory.NOVEL));
    addBookWithTwoCopies(new Book("And Then There Were None", "978-0312330873", List.of("Agatha Christie"), BookCategory.NOVEL));
    addBookWithTwoCopies(new Book("Dream of the Red Chamber", "978-0140443714", List.of("Cao Xueqin"), BookCategory.NOVEL));
    addBookWithTwoCopies(new Book("The Hobbit", "978-0261103283", List.of("J.R.R. Tolkien"), BookCategory.NOVEL));
    addBookWithTwoCopies(new Book("She: A History of Adventure", "978-0199536425", List.of("H. Rider Haggard"),
      BookCategory.NOVEL));
    addBookWithTwoCopies(new Book("The Lion, the Witch and the Wardrobe", "978-0006716631", List.of("C.S. Lewis"),
      BookCategory.NOVEL));
    addBookWithTwoCopies(new Book("The Da Vinci Code", "978-0307474278", List.of("Dan Brown"), BookCategory.NOVEL));
    addBookWithTwoCopies(new Book("The Catcher in the Rye", "978-0316769488", List.of("J.D. Salinger"), BookCategory.NOVEL));
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
