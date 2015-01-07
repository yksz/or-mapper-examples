package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

interface Dao<T, PK extends Serializable> {

    PK save(T t);
    T get(PK id);
    void update(T t);
    void delete(T t);

    List<T> findAll();
    List<T> findByCriteria(Criterion criterion);
    List<T> findByHQL(String hql, Object... objs);
    int deleteByHQL(String hql, Object... objs);

}
