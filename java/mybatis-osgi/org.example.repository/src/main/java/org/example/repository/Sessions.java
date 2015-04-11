package org.example.repository;

import org.apache.ibatis.session.SqlSession;

class Sessions {

    public static void withTransaction(SqlSession session, Runnable func) {
        try {
            func.run();
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

}
