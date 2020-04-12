package cl.prez.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import cl.prez.hibernate.model.util.Constant;

public class GenericCrud<T, I> implements Crud<T,I>{

    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    protected Query query;

    public GenericCrud(){
        entityManagerFactory = Persistence.createEntityManagerFactory(Constant.PERSISTENCE_NAME);
        entityManager        = entityManagerFactory.createEntityManager();
    }

	@Override
	public T create(T object) {
		return null;
	}

	@Override
	public List<T> findAll() {
        query = entityManager.createQuery("FROM "+T.getClass().getSimpleName(), T.getClass());
		return query.getResultList();
	}

	@Override
	public T update(T object) {
		return null;
	}

	@Override
	public void deleteById(I id) {

	}

	@Override
	public T findById(I id) {
		return null;
	}

}