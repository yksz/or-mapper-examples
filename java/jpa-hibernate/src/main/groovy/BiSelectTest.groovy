import static query.Queries.*
import static query.QueryUtils.*

import javax.persistence.EntityManager
import javax.persistence.Persistence
import javax.persistence.Query

import entity.bidirectional.*

class BiSelectTest {

    static main(args) {
        // Eager
        query({ createTable(it, 10, { new EagerParent() }, { new EagerChild() }) }, 'createTable:')

        query({ findParents(it, EagerParent.class) }, 'findEagerParents:') // N + 1 (or 2)
        query({ findParentsByJoinFetch(it, EagerParent.class) }, 'findEagerParentsByJoinFetch:')
        query({ findChildren(it, EagerChild.class, true) }, 'findEagerChildren:') // N + 1 (or N + M + 1)
        query({ findChildrenByJoinFetch(it, EagerChild.class, true) }, 'findEagerChildrenByJoinFetch:')

        // Lazy
        query({ createTable(it, 10, { new LazyParent() }, { new LazyChild() }) }, 'createTable:')

        query({ findParents(it, LazyParent.class) }, 'findLazyParents:') // N + 1
        query({ findParentsByJoinFetch(it, LazyParent.class) }, 'findLazyParentsByJoinFetch:')
        query({ findChildren(it, LazyChild.class, true) }, 'findLazyChildren:') // N + 1
        query({ findChildrenByJoinFetch(it, LazyChild.class, true) }, 'findLazyChildrenByJoinFetch:')
    }

}
