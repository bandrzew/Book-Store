package pl.coderslab.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByTitle(String title);

	List<Book> findByTitleLike(String wildcard); // {"%", "_"}

	List<Book> findByPublisher(Publisher publisher);

	List<Book> findByPublisherId(Long id);

	List<Book> findByAuthors(Author author);

	List<Book> findByRating(BigDecimal rating);

}
