package dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import entity.Book;

public class BookDaoImpl extends DaoImpl<Book, Long> implements BookDao {

    public BookDaoImpl() {
        super(Book.class);
    }

    @Override
    public List<Book> findByTitle(String title) throws Exception {
        return findByCriteria(Restrictions.like("title", title, MatchMode.EXACT));
    }

    @Override
    public List<Book> findByAuthor(String author) throws Exception {
        return findByHQL("FROM Book b WHERE b.author = ?", author);
    }

}
