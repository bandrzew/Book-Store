package pl.coderslab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Author;
import pl.coderslab.entity.Person;
import pl.coderslab.entity.Publisher;

@Component
@Transactional
public class PublisherDao {
	@PersistenceContext
	EntityManager em;

	public void save(Publisher entity) {
		this.em.persist(entity);
	}

	public void update(Publisher entity) {
		this.em.merge(entity);
	}

	public Publisher findById(long id) {
		return this.em.find(Publisher.class, id);
	}

	public void delete(Publisher entity) {
		this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
	}

	public List<Publisher> getAll() {
		Query query = this.em.createQuery("SELECT e FROM Publisher e");
		return query.getResultList();
	}
}
