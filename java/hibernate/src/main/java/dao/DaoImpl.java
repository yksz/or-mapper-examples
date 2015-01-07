package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

class DaoImpl<T, PK extends Serializable> implements Dao<T, PK> {

    private Class<T> entityClass;

    public DaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Session getSession() throws Exception {
        return SessionManager.openSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public PK save(T t) throws Exception {
        return (PK) getSession().save(t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(PK id) throws Exception {
        return (T) getSession().get(entityClass, id);
    }

    @Override
    public void update(T t) throws Exception {
        getSession().update(t);
    }

    @Override
    public void delete(T t) throws Exception {
        getSession().delete(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return findByCriteria(null);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion criterion) throws Exception {
        Criteria criteria = getSession().createCriteria(entityClass);
        if (criterion != null)
            criteria.add(criterion);
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByHQL(String hql, Object... objs) throws Exception {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < objs.length; i++)
            query.setParameter(i, objs[i]);
        return query.list();
    }

    @Override
    public int deleteByHQL(String hql, Object... objs) throws Exception {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < objs.length; i++)
            query.setParameter(i, objs[i]);
        return query.executeUpdate();
    }

}
