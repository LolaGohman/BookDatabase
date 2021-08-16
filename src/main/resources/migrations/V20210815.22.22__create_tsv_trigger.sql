CREATE INDEX textsearch_idx ON book USING GIN (tsv);

CREATE OR REPLACE FUNCTION tsvector_update_books()
    RETURNS TRIGGER AS  $$
BEGIN
    new.tsv :=
                to_tsvector(new.title);
    RETURN new;
END $$ LANGUAGE 'plpgsql';

CREATE TRIGGER tsvectorupdatetrigger
    BEFORE
INSERT OR
UPDATE
    ON book
    FOR EACH ROW
EXECUTE PROCEDURE
        tsvector_update_books();