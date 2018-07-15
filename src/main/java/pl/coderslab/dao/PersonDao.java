package pl.coderslab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.entity.Person;

@Component
@Transactional
public class PersonDao {
	@PersistenceContext
	EntityManager em;
	
	public void save(Person entity) {
		this.em.persist(entity);
	}

	public void update(Person entity) {
		this.em.merge(entity);
	}

	public Person findById(long id) {
		return this.em.find(Person.class, id);
	}

	public void delete(Person entity) {
		this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
	}
	
	public List<Person> getAll() {
		Query query = this.em.createQuery("SELECT e FROM Person e");
		return query.getResultList();
	}
}
