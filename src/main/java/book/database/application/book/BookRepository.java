package book.database.application.book;

import book.database.application.parser.Book;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query("SELECT * FROM book WHERE title LIKE :term")
    Set<Book> findByTitle(@Param("term") String term);

    @Query("SELECT * FROM book WHERE phraseto_tsquery(:term) @@ tsv")
    Set<Book> findByTitleFullText(@Param("term") String term);
}
