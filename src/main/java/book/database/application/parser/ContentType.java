package book.database.application.parser;

import java.util.HashMap;
import java.util.Map;

public enum ContentType {
    IMG_PNG("image/png"),
    IMG_JPEG("image/jpeg"),
    FB2_ZIP("application/fb2+zip"),
    HTML_ZIP("application/html+zip"),
    TXT_ZIP("application/txt+zip"),
    RTF_ZIP("application/rtf+zip"),
    EPUB_ZIP("application/epub+zip"),
    PDF("application/pdf"),
    EPUB("application/epub"),
    PDF_ZIP("application/pdf+zip"),
    INNER_LINK("application/atom+xml"),
    UNSUPPORTED("unsupported");

    private static final Map<String, ContentType> CONTENT_TYPE_BY_TYPE_NAME = new HashMap<>();

    static {
        for (ContentType type : values()) {
            CONTENT_TYPE_BY_TYPE_NAME.put(type.getTypeName(), type);
        }
    }

    public static ContentType getByTypeName(String typeName) {
        return CONTENT_TYPE_BY_TYPE_NAME.get(typeName);
    }

    private final String typeName;

    ContentType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public boolean isDownloadLink() {
        return this == FB2_ZIP ||
            this == HTML_ZIP ||
            this == TXT_ZIP ||
            this == RTF_ZIP ||
            this == EPUB_ZIP ||
            this == PDF ||
            this == EPUB ||
            this == PDF_ZIP ||
            this == UNSUPPORTED;
    }

    public boolean isInnerLink() {
        return this == INNER_LINK;
    }

    public boolean isImage() {
        return this == IMG_PNG || this == IMG_JPEG;
    }
}
