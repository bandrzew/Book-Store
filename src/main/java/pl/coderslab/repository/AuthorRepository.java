package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	List<Author> findByLastName(String lastName);
}
