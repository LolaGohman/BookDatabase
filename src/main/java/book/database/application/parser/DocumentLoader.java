package book.database.application.parser;

import book.database.application.exception.DocumentLoadException;
import com.ning.http.client.Response;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING;
import static javax.xml.parsers.DocumentBuilderFactory.newInstance;

@Component
public class DocumentLoader {

    private final Connector connector;

    public DocumentLoader(Connector connector) {
        this.connector = connector;
    }

    Document loadPage(String link) throws DocumentLoadException {
        final Response response = connector.executeRequest(link);
        final DocumentBuilderFactory factory = newInstance();
        try {
            factory.setFeature(FEATURE_SECURE_PROCESSING, true);
            final DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(response.getResponseBodyAsStream());
        } catch (Exception e) {
            //add logging
           throw new DocumentLoadException();
        }
    }
}
