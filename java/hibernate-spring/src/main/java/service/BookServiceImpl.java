package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BookDao;
import entity.Book;

@Service
@Transactional(readOnly = true)
class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional(readOnly = false)
    public Long save(Book book) throws Exception {
        return bookDao.save(book);
    }

    @Override
    public List<Book> findAll() throws Exception {
        return bookDao.findByCriteria(null);
    }

    @Override
    public List<Book> findByTitle(String title) throws Exception {
        return bookDao.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) throws Exception {
        return bookDao.findByAuthor(author);
    }

}
