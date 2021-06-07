package book.database.application.service;

import book.database.application.parser.LinkParser;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final LinkParser linkParser;

    public BookService(LinkParser linkParser) {
        this.linkParser = linkParser;
    }


}
