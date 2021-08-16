CREATE TABLE book
(
    book_id  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title    VARCHAR NOT NULL,
    author   VARCHAR NOT NULL,
    summary  VARCHAR,
    category VARCHAR,
    tsv      TSVECTOR
);