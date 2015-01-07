package query

import javax.persistence.EntityManager
import javax.persistence.Query

class Queries {

    static def createTable(EntityManager em, int recordNum, Closure newParent, Closure newChild) {
        try {
            em.getTransaction().begin()
            for (i in 1..recordNum) {
                def c1 = newChild()
                c1.setName("c${2*i - 1}")
                def c2 = newChild()
                c2.setName("c${2*i}")

                def p = newParent()
                p.setName("p${i}")
                p.addChild(c1)
                p.addChild(c2)
                em.persist(p)
            }
            em.getTransaction().commit()
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback()
        }
    }

    static def findParents(EntityManager em, Class clazz) {
        Query query = em.createQuery("SELECT p FROM ${clazz.canonicalName} p", clazz)
        def p = query.getResultList()
        println p
        println p.children
    }

    static def findParentsByJoinFetch(EntityManager em, Class clazz) {
        Query query = em.createQuery("SELECT p FROM ${clazz.canonicalName} p JOIN FETCH p.children", clazz)
        def p = query.getResultList()
        println p
        println p.children
    }

    static def findChildren(EntityManager em, Class clazz, boolean isBidirectional) {
        Query query = em.createQuery("SELECT c FROM ${clazz.canonicalName} c", clazz)
        def c = query.getResultList()
        println c
        if (isBidirectional)
            println c.parent
    }

    static def findChildrenByJoinFetch(EntityManager em, Class childClass, boolean isBidirectional) {
        Query query = em.createQuery("SELECT c FROM ${childClass.canonicalName} c JOIN FETCH c.parent p JOIN FETCH p.children")
        def c = query.getResultList()
        println c
        if (isBidirectional)
            println c.parent
    }

}
