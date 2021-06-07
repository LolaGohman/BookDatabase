package book.database.application.parser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public enum BookElement implements TagElement<Book> {
    ID("id") {
        @Override
        public void setElement(Book book, Node idNode) {
            setNodeValue(idNode, book::setId);
        }
    },
    TITLE("title") {
        @Override
        public void setElement(Book book, Node titleNode) {
            setNodeValue(titleNode, book::setTitle);
        }
    },
    SUMMARY("summary") {
        @Override
        public void setElement(Book book, Node summaryNode) {
           setNodeValue(summaryNode, book::setSummary);
        }
    },
    AUTHOR("author") {
        private static final String AUTHOR_NODE_NAME = "name";

        @Override
        public void setElement(Book book, Node authorNode) {
            final NodeList childNodes = authorNode.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                final Node item = childNodes.item(i);
                if (AUTHOR_NODE_NAME.equals(item.getNodeName()))
                    setNodeValue(item, book::setAuthor);
            }
        }
    },
    CATEGORY("category") {
        @Override
        public void setElement(Book book, Node categoryNode) {
            setNodeValue(categoryNode, book::setCategory);
        }
    };

    private static final Map<String, BookElement> ELEMENT_BY_TAG_NAME = new HashMap<>();

    static {
        for (BookElement element : values()) {
            ELEMENT_BY_TAG_NAME.put(element.tagName, element);
        }
    }

    private final String tagName;

    BookElement(String tagName) {
        this.tagName = tagName;
    }

    static BookElement getBookElementByTagName(String tagName) {
        return ELEMENT_BY_TAG_NAME.get(tagName);
    }

    private static void setNodeValue(Node bookElementNode, Consumer<String> setElement) {
        final Node firstChild = bookElementNode.getFirstChild();
        if (firstChild != null) {
            final String bookElement = firstChild.getNodeValue();
            if (!isEmpty(bookElement))
                setElement.accept(bookElement);
        }
    }
}
