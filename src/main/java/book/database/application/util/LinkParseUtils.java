package book.database.application.util;

import book.database.application.parser.ContentType;
import org.w3c.dom.Node;

import java.net.MalformedURLException;
import java.net.URL;

import static book.database.application.parser.ContentType.INNER_LINK;
import static book.database.application.parser.ContentType.UNSUPPORTED;
import static book.database.application.parser.ContentType.getByTypeName;

public final class LinkParseUtils {
    private static final String CONTENT_TYPE = "type";
    private static final String HREF = "href";

    private LinkParseUtils() {
        //hides public constructor
    }

    public static ContentType getContentType(Node linkElement) {
        final String contentTypeName = linkElement.getAttributes().getNamedItem(CONTENT_TYPE).getNodeValue();
        if (contentTypeName.startsWith(INNER_LINK.getTypeName())) {
            return INNER_LINK;
        }
        final ContentType contentType = getByTypeName(contentTypeName);
        return contentType != null ? contentType : UNSUPPORTED;
    }

    public static String getUrl(String mainPath, Node linkElement) throws MalformedURLException {
        final String relativePath = linkElement.getAttributes().getNamedItem(HREF).getNodeValue();
        final URL url = new URL(mainPath);
        return new URL(url, relativePath).toString();
    }
}
