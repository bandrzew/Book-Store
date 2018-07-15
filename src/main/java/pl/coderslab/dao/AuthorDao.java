package pl.coderslab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Author;

@Component
@Transactional
public class AuthorDao {
	@PersistenceContext
	EntityManager em;
	
	public void save(Author entity) {
		this.em.persist(entity);
	}

	public void update(Author entity) {
		this.em.merge(entity);
	}

	public Author findById(long id) {
		return this.em.find(Author.class, id);
	}

	public void delete(Author entity) {
		this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
	}
	
	public List<Author> getAll() {
		Query query = this.em.createQuery("SELECT e FROM Author e");
		return query.getResultList();
	}
}
