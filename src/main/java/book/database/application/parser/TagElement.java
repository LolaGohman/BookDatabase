package book.database.application.parser;

import org.w3c.dom.Node;

public interface TagElement<T> {
    void setElement(T object, Node elementNode);
}
