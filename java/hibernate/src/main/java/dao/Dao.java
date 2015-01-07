package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

interface Dao<T, PK extends Serializable> {

    PK save(T t) throws Exception;
    T get(PK id) throws Exception;
    void update(T t) throws Exception;
    void delete(T t) throws Exception;

    List<T> findAll() throws Exception;
    List<T> findByCriteria(Criterion criterion) throws Exception;
    List<T> findByHQL(String hql, Object... objs) throws Exception;
    int deleteByHQL(String hql, Object... objs) throws Exception;

}
