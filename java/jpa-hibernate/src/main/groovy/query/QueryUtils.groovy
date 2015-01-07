package query

import javax.persistence.EntityManager
import javax.persistence.Persistence

class QueryUtils {

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

}
