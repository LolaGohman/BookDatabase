package book.database.application.controller;

import book.database.application.book.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class BookController {

    private static final String BOOK_LIST = "bookList";
    private static final String BOOKS = "books";
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/parseLink")
    public ModelAndView parse(@RequestParam String link) throws IOException {
        bookService.save(link);
        return new ModelAndView(BOOK_LIST)
            .addObject(BOOKS, bookService.getAllBooks());
    }

    @GetMapping("/getByTitle")
    public ModelAndView getByTitle(@RequestParam String title) {
        return new ModelAndView(BOOK_LIST)
            .addObject(BOOKS, bookService.getByTitle(title));
    }

    @GetMapping("/getByTitleFullText")
    public ModelAndView getByDescription(@RequestParam String title) {
        return new ModelAndView(BOOK_LIST)
            .addObject("books", bookService.getByTitleFullText(title));
    }
}
