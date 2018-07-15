package pl.coderslab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;

@Component
@Transactional
public class BookDao {
	@PersistenceContext
	EntityManager em;

	public void save(Book entity) {
		this.em.persist(entity);
	}

	public void update(Book entity) {
		this.em.merge(entity);
	}

	public Book findById(long id) {
		return this.em.find(Book.class, id);
	}

	public void delete(Book entity) {
		this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
	}

	public List<Book> getAll() {
		Query query = this.em.createQuery("SELECT e FROM Book e");
		return query.getResultList();
	}

	public List<Book> getPropositions() {
		Query query = this.em.createQuery("SELECT e FROM Book e WHERE proposition = 1");
		return query.getResultList();
	}
}
