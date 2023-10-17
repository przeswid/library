package com.pswida.library.catalog;

import com.pswida.library.catalog.domain.book.BookCategory;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.book.BookSnapshot;
import com.pswida.library.catalog.domain.bookcopy.BookCopyId;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import com.pswida.library.catalog.application.book.commands.AddBookCommand;
import com.pswida.library.catalog.application.bookcopy.commands.AddBookCopyCommand;
import com.pswida.library.catalog.application.core.cqs.command.CommandDispatcher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class InitDatabase implements ApplicationListener<ContextRefreshedEvent> {

  private final CommandDispatcher commandDispatcher;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    initDatabase();
  }

  @PostConstruct
  private void initDatabase() {
    String isbn = "978-0261103252";
    addBook("The Lord of the Rings", isbn, List.of("J.R.R. Tolkien"), BookCategory.NOVEL);
    addBookCopy(isbn);
    addBookCopy(isbn);

    addBook("Le Petit Prince", "978-2070612758", List.of("Antoine de Saint-Exupéry"), BookCategory.NOVEL);
    addBook("Harry Potter and the Philosopher's Stone", "978-0747532743", List.of("J.K. Rowling"), BookCategory.NOVEL);
    addBook("And Then There Were None", "978-0312330873", List.of("Agatha Christie"), BookCategory.NOVEL);
    addBook("Dream of the Red Chamber", "978-0140443714", List.of("Cao Xueqin"), BookCategory.NOVEL);
    addBook("The Hobbit", "978-0261103283", List.of("J.R.R. Tolkien"), BookCategory.NOVEL);
    addBook("She: A History of Adventure", "978-0199536425", List.of("H. Rider Haggard"), BookCategory.NOVEL);
    addBook("The Lion, the Witch and the Wardrobe", "978-0006716631", List.of("C.S. Lewis"), BookCategory.NOVEL);
    addBook("The Da Vinci Code", "978-0307474278", List.of("Dan Brown"), BookCategory.NOVEL);
    addBook("The Catcher in the Rye", "978-0316769488", List.of("J.D. Salinger"), BookCategory.NOVEL);
  }

  private void addBook(String title, String isbn, List<String> authors, BookCategory category) {
    BookSnapshot bookSnapshot = BookSnapshot.builder()
      .title(title)
      .isbn(new BookIsbn(isbn))
      .authors(authors)
      .bookCategory(category)
      .build();
    commandDispatcher.dispatch(new AddBookCommand(bookSnapshot));
  }

  private void addBookCopy(String bookIsbn) {
    commandDispatcher.dispatch(
      new AddBookCopyCommand(new BookIsbn(bookIsbn), new BookCopyId(UUID.randomUUID().toString())));
  }
}

