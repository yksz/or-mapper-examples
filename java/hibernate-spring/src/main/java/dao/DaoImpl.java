package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

class DaoImpl<T, PK extends Serializable> implements Dao<T, PK> {

    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    public DaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public PK save(T t) {
        return (PK) getSession().save(t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        return (T) getSession().get(entityClass, id);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @Override
    public List<T> findAll() {
        return findByCriteria(null);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion criterion) {
        Criteria criteria = getSession().createCriteria(entityClass);
        if (criterion != null)
            criteria.add(criterion);
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByHQL(String hql, Object... objs) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < objs.length; i++)
            query.setParameter(i, objs[i]);
        return query.list();
    }

    @Override
    public int deleteByHQL(String hql, Object... objs) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < objs.length; i++)
            query.setParameter(i, objs[i]);
        return query.executeUpdate();
    }

}
