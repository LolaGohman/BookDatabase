package book.database.application.parser;

import book.database.application.link.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ParsedInfo {
    private final String link;
    private final List<Book> books = new ArrayList<>();
    private final List<Link> innerLinks = new ArrayList<>();

    public ParsedInfo(String link) {
        this.link = link;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Link> getInnerLinks() {
        return innerLinks;
    }

    public void addLinks(Set<Link> links) {
        this.innerLinks.addAll(links);
    }

    public String getLink() {
        return link;
    }
}
