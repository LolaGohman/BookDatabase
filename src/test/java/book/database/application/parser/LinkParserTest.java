package book.database.application.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING;
import static javax.xml.parsers.DocumentBuilderFactory.newInstance;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkParserTest {
    private static final String URL = "http://host";
    private DocumentLoader documentLoader;
    private LinkParser linkParser;

    @BeforeEach
    public void setUp() {
        documentLoader = mock(DocumentLoader.class);
        linkParser = new LinkParser(documentLoader);
    }

    @Test
    void shouldParseABook() throws Exception {
        final Document bookPage = generateDocument("test.xml");
        when(documentLoader.loadPage(URL)).thenReturn(bookPage);
        final List<Book> result = linkParser.getBooks(URL);
        final Book book1 = new Book();
        final List<Book> expectedResult = linkParser.getBooks(URL);
        System.out.println(bookPage);

    }

    private Document generateDocument(String content) {
        final DocumentBuilderFactory factory = newInstance();
        try {
            factory.setFeature(FEATURE_SECURE_PROCESSING, true);
            final DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(getClass().getClassLoader().getResourceAsStream(content));
        } catch (Exception e) {
            System.out.println();
            //add logging
        }
        return null;
    }

}