import java.util.List;

import entity.Book;
import service.BookService;
import service.BookServiceImpl;

public class Sample {

    public static void main(String[] args) throws Exception {
        Book book = new Book();
        book.setTitle("foo");
        book.setAuthor("bar");

        BookService bookService = new BookServiceImpl();
        bookService.save(book);
        show(bookService.findAll(), "findAll:");
    }

    static <T> void show(List<T> objs, String msg) {
        System.out.println(msg);
        for (T obj : objs)
            System.out.println(obj);
    }

}
