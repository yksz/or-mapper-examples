package service;

import java.util.List;

import dao.BookDao;
import dao.BookDaoImpl;
import dao.SessionManager;
import entity.Book;

public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public BookServiceImpl() {
        bookDao = new BookDaoImpl();
    }

    @Override
    public Long save(Book book) throws Exception {
        if (book == null)
            throw new NullPointerException("book must not be null");

        try {
            SessionManager.beginTransaction();
            Long pk = bookDao.save(book);
            SessionManager.commit();
            return pk;
        } catch (Exception e) {
            SessionManager.rollback();
            throw e;
        } finally {
            SessionManager.closeSession();
        }
    }

    @Override
    public List<Book> findAll() throws Exception {
        try {
            return bookDao.findByCriteria(null);
        } finally {
            SessionManager.closeSession();
        }
    }

    @Override
    public List<Book> findByTitle(String title) throws Exception {
        if (title == null)
            throw new NullPointerException("title must not be null");

        try {
            return bookDao.findByTitle(title);
        } finally {
            SessionManager.closeSession();
        }
    }

    @Override
    public List<Book> findByAuthor(String author) throws Exception {
        if (author == null)
            throw new NullPointerException("author must not be null");

        try {
            return bookDao.findByAuthor(author);
        } finally {
            SessionManager.closeSession();
        }
    }

}
