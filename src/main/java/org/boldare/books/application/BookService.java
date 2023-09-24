package org.boldare.books.application;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.boldare.books.application.time.DateTimeProvider;
import org.boldare.books.domain.book.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class BookService {

  private final BookRepository bookRepository;

  private final DateTimeProvider realDateTimeProvider;

  public Collection<Book> findBookByTitle(String title) {
    return bookRepository.searchByTitle(title);
  }

  public void borrowBookCopy(String title, String bookCopyId) {
    Book book = bookRepository.getByTitle(title).orElseThrow(() -> new IllegalStateException("Book not found"));
    book.borrowBookCopy(bookCopyId, realDateTimeProvider.getCurrentDateTime());
  }

  public Collection<Book> findAllBooks() {
    return bookRepository.getAll();
  }

  @PostConstruct
  private void initDatabase() {
    addBookWithTwoCopies("The Lord of the Rings", "978-0261103252", List.of("J.R.R. Tolkien"), BookCategory.NOVEL);
    addBookWithTwoCopies("Le Petit Prince", "978-2070612758", List.of("Antoine de Saint-Exupéry"), BookCategory.NOVEL);
    addBookWithTwoCopies("Harry Potter and the Philosopher's Stone", "978-0747532743", List.of("J.K. Rowling"),
      BookCategory.NOVEL);
    addBookWithTwoCopies("And Then There Were None", "978-0312330873", List.of("Agatha Christie"), BookCategory.NOVEL);
    addBookWithTwoCopies("Dream of the Red Chamber", "978-0140443714", List.of("Cao Xueqin"), BookCategory.NOVEL);
    addBookWithTwoCopies("The Hobbit", "978-0261103283", List.of("J.R.R. Tolkien"), BookCategory.NOVEL);
    addBookWithTwoCopies("She: A History of Adventure", "978-0199536425", List.of("H. Rider Haggard"),
      BookCategory.NOVEL);
    addBookWithTwoCopies("The Lion, the Witch and the Wardrobe", "978-0006716631", List.of("C.S. Lewis"),
      BookCategory.NOVEL);
    addBookWithTwoCopies("The Da Vinci Code", "978-0307474278", List.of("Dan Brown"), BookCategory.NOVEL);
    addBookWithTwoCopies("The Catcher in the Rye", "978-0316769488", List.of("J.D. Salinger"), BookCategory.NOVEL);
  }

  private void addBookWithTwoCopies(String title, String isbn, List<String> authors, BookCategory category) {
    BookSnapshot bookSnapshot = BookSnapshot.builder()
      .title(title)
      .isbn(isbn)
      .authors(authors)
      .bookCategory(category)
      .copies(Map.of(isbn + "-1", BookCopySnapshot.builder().build(), isbn + "-2", BookCopySnapshot.builder().build()))
      .build();
    Book book = Book.fromSnapshot(bookSnapshot);

    bookRepository.add(book);
  }

}
