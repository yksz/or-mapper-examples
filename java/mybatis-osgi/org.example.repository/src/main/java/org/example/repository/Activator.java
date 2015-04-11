package org.example.repository;

import java.util.List;

import org.example.dao.entity.Book;
import org.example.dao.entity.Book.Category;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Book book = new Book();
        book.setTitle("foo");
        book.setAuthor("bar");

        BookRepository bookRepository = new BookRepositoryImpl();
        System.out.println("save: id=" + bookRepository.save(book));
        show(bookRepository.findAll(), "findAll:");
        show(bookRepository.findByTitle("foo"), "findByTitle:");
        show(bookRepository.findByAuthor("bar"), "findByAuthor:");
        show(bookRepository.findByCategory(Category.UNKNOWN), "findByCategory:");
    }

    static <T> void show(List<T> objs, String msg) {
        System.out.println(msg);
        for (T obj : objs)
            System.out.println(obj);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }

}
