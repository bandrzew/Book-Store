package pl.coderslab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
@Transactional
public class MainDao<T> {
	@PersistenceContext
	EntityManager entityManager;

	public void add(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(Class<T> t, long id) {
		T entity = findById(t, id);
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	public T findById(Class<T> t, long id) {
		return (T) entityManager.find(t, id);
	}

	public List<T> getList(Class<T> t) {
		Query query = entityManager.createQuery(String.format("SELECT T FROM %s T", t.getSimpleName())); //Wszystkie połączenia muszą mieć fetch = FetchType.EAGER
		List<T> list = (List<T>) query.getResultList();
		return list;
	}
}
