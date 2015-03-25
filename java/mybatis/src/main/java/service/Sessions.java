package service;

import java.util.function.Supplier;

import org.apache.ibatis.session.SqlSession;

class Sessions {

    public static <T> T withTransaction(SqlSession session, Supplier<T> func) {
        try {
            T result = func.get();
            session.commit();
            return result;
        } catch (Exception e) {
            session.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

}
