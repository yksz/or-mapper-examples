package dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import entity.Book;

@Repository
class BookDaoImpl extends DaoImpl<Book, Long> implements BookDao {

    public BookDaoImpl() {
        super(Book.class);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return findByCriteria(Restrictions.like("title", title, MatchMode.EXACT));
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return findByHQL("FROM Book b WHERE b.author = ?", author);
    }

}
