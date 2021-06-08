package book.database.application.controller;

import book.database.application.book.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/parseLink")
    public ModelAndView parse(@RequestParam String link) throws IOException {
        bookService.save(link);
        return new ModelAndView("bookList")
            .addObject("books", bookService.getAllBooks());
    }
}
