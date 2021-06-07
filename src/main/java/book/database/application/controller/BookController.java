package book.database.application.controller;

import book.database.application.parser.LinkParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.xml.parsers.ParserConfigurationException;

@Controller
public class BookController {

    private final LinkParser linkParser;

    public BookController(LinkParser linkParser) {
        this.linkParser = linkParser;
    }

    @GetMapping("/parseLink")
    public ModelAndView parse(@RequestParam String link) throws IOException, SAXException, ParserConfigurationException {
        final var result = linkParser.getBooks(link);
        return new ModelAndView("bookList")
            .addObject("books", result);
    }
}
