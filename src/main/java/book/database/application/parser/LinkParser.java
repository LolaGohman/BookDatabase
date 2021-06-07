package book.database.application.parser;

import book.database.application.exception.DocumentLoadException;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static book.database.application.parser.BookElement.getBookElementByTagName;
import static book.database.application.util.CollectionUtils.isNullOrEmpty;
import static book.database.application.util.LinkParseUtils.getContentType;
import static book.database.application.util.LinkParseUtils.getUrl;
import static java.util.stream.Collectors.toSet;

@Component
public class LinkParser {
    private static final String ENTRY_NODE_NAME = "entry";
    private static final String LINK_NODE_NAME = "link";

    private final DocumentLoader documentLoader;

    public LinkParser(DocumentLoader documentLoader) {
        this.documentLoader = documentLoader;
    }

    public List<Book> getBooks(String link) throws IOException {
        return getBooks(link, new ArrayList<>());
    }

    private List<Book> getBooks(String link, List<Book> books) throws IOException {
        final Document startPage;
        try {
            startPage = documentLoader.loadPage(link);
        } catch (DocumentLoadException e) {
            return new ArrayList<>();
        }

        final ParsedInfo parsedInfo = parsePage(link, startPage);

        if (!parsedInfo.getBooks().isEmpty()) {
            books.addAll(parsedInfo.getBooks());
        }

        for (Link innerLink : parsedInfo.getInnerLinks()) {
            getBooks(innerLink.getUrl(), books);
        }

        return books;
    }

    private ParsedInfo parsePage(String link, Document page) throws MalformedURLException {
        final ParsedInfo parseInfo = new ParsedInfo(link);
        final NodeList entries = page.getDocumentElement().getElementsByTagName(ENTRY_NODE_NAME);
        for (int i = 0; i < entries.getLength(); i++) {
            final Node entry = entries.item(i);
            parseEntry(entry, parseInfo);
        }
        return parseInfo;
    }

    private void parseEntry(Node entry, ParsedInfo parsedInfo) throws MalformedURLException {
        final Set<Link> links = getAllLinks(parsedInfo.getLink(), entry);
        final Set<Link> bookLinks = links.stream().filter(link -> link.getContentType().isDownloadLink()).collect(toSet());

        if (!isNullOrEmpty(bookLinks)) {
            final Book book = parseBook(entry);
            final Set<Link> imageLinks = links.stream().filter(link -> link.getContentType().isImage()).collect(toSet());
            book.setDownloadLinks(bookLinks);
            book.setImageLinks(imageLinks);
            parsedInfo.getBooks().add(book);
        } else {
            final Set<Link> innerLinks = links.stream().filter(link -> link.getContentType().isInnerLink()).collect(toSet());
            if (!isNullOrEmpty(innerLinks)) {
                parsedInfo.addLinks(innerLinks);
            }
        }
    }

    private Book parseBook(Node entry) {
        final NodeList bookElements = entry.getChildNodes();
        final Book book = new Book();
        for (int i = 0; i < bookElements.getLength(); i++) {
            final Node element = bookElements.item(i);
            final String tagName = element.getNodeName();
            final BookElement bookElement = getBookElementByTagName(tagName);
            if (bookElement != null) {
                bookElement.setElement(book, element);
            }
        }
        return book;
    }

    private Set<Link> getAllLinks(String path, Node entry) throws MalformedURLException {
        final Set<Link> links = new HashSet<>();
        final NodeList entryElements = entry.getChildNodes();
        for (int i = 0; i < entryElements.getLength(); i++) {
            final Node element = entryElements.item(i);
            if (LINK_NODE_NAME.equals(element.getNodeName())) {
                final ContentType contentType = getContentType(element);
                final String url = getUrl(path, element);
                final Link link = new Link(url, contentType);
                links.add(link);
            }
        }
        return links;
    }
}
