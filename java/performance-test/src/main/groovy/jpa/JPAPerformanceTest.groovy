package jpa

import javax.persistence.CascadeType
import javax.persistence.EntityManager
import javax.persistence.Persistence
import javax.persistence.Query
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

import jpa.entity.Product;

class JPAPerformanceTest {

    private static final RECORD_NUM = 10000
    private static final SHOWS = false
    private static final PERSISTENCE_UNIT_NAME = "test"

    static def query(Closure closure, String msg) {
        def emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
        def em = emf.createEntityManager()
        try {
            println msg
            long start = System.currentTimeMillis()
            closure(em)
            long stop = System.currentTimeMillis()
            println "${stop - start}[ms]\n"
        } finally {
            em.close()
            emf.close()
        }
    }

    static def createTable(EntityManager em) {
        try {
            em.getTransaction().begin()
            for (i in 1..RECORD_NUM) {
                def p = new Product()
                p.setName("p${i}")
                em.persist(p)
            }
            em.getTransaction().commit()
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback()
        }
    }

    static def selectByEntity(EntityManager em) {
        for (i in 1..RECORD_NUM) {
            def p = em.find(Product.class, (long) i)
            if (p)
                show p
        }
    }

    static def selectByCriteria(EntityManager em) {
        for (i in 1..RECORD_NUM) {
            CriteriaBuilder cb = em.getCriteriaBuilder()
            CriteriaQuery<Product> cq = cb.createQuery(Product.class)
            Root<Product> parent = cq.from(Product.class)
            cq.select(parent).where(cb.equal(parent.get("id"), i))
            def p = em.createQuery(cq).getResultList()
            show p
        }
    }

    static def selectByJPQL(EntityManager em) {
        for (i in 1..RECORD_NUM) {
            Query query = em.createQuery("SELECT p FROM Product p WHERE p.id = ?1", Product.class)
            query.setParameter(1, (long) i)
            def p = query.getResultList()
            show p
        }
    }

    static def selectByNamedJPQL(EntityManager em) {
        for (i in 1..RECORD_NUM) {
            Query query = em.createNamedQuery(Product.FIND_BY_NAMED_QUERY)
            query.setParameter(1, (long) i)
            def p = query.getResultList()
            show p
        }
    }

    static def selectByNativeSQL(EntityManager em) {
        for (i in 1..RECORD_NUM) {
            Query query = em.createNativeQuery("SELECT * FROM Product WHERE id = ?", Product.class)
            query.setParameter(1, (long) i)
            def p = query.getResultList()
            show p
        }
    }

    static def selectByNamedNativeSQL(EntityManager em) {
        for (i in 1..RECORD_NUM) {
            Query query = em.createNamedQuery(Product.FIND_BY_NAMED_NATIVE_QUERY)
            query.setParameter(1, (long) i)
            def p = query.getResultList()
            show p
        }
    }

    static def show(obj) {
        if (SHOWS)
            println obj
    }

    static main(args) {
        query({ createTable(it) }, 'createTable:')

        query({ selectByEntity(it) }, 'selectByEntity:')
        query({ selectByCriteria(it) }, 'selectByCriteria:')
        query({ selectByJPQL(it) }, 'selectByJPQL:')
        query({ selectByNamedJPQL(it) }, 'selectByNamedJPQL:')
        query({ selectByNativeSQL(it) }, 'selectByNativeSQL:')
        query({ selectByNamedNativeSQL(it) }, 'selectByNamedNativeSQL:')
    }

}
