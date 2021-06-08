CREATE TABLE link
(
    link_id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    url          VARCHAR NOT NULL,
    content_type VARCHAR NOT NULL,
    book_id BIGINT DEFAULT NULL,
    CONSTRAINT BRANCH_ID_FK
        FOREIGN KEY (book_id)
            REFERENCES book (book_id)
);
