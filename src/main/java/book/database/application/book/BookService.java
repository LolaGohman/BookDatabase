package book.database.application.book;

import book.database.application.parser.Book;
import book.database.application.parser.LinkParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class BookService {
    private final LinkParser linkParser;
    private final BookRepository bookRepository;

    public BookService(LinkParser linkParser, BookRepository bookRepository) {
        this.linkParser = linkParser;
        this.bookRepository = bookRepository;
    }

    public void save(String link) throws IOException {
        final var books = linkParser.getBooks(link);
        bookRepository.saveAll(books);
    }

    public Set<Book> getAllBooks() {
        return bookRepository.findAll();
    }


}
