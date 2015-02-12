import java.util.List;

import service.BookService;
import service.ServiceFactory;
import entity.Book;

public class Sample {

    public static void main(String[] args) throws Exception {
        Book book = new Book();
        book.setTitle("foo");
        book.setAuthor("bar");

        BookService bookService = ServiceFactory.getBookService();
        System.out.println("save: id=" + bookService.save(book));
        show(bookService.findAll(), "findAll:");
        show(bookService.findByTitle("foo"), "findByTitle:");
        show(bookService.findByAuthor("bar"), "findByAuthor:");
    }

    static <T> void show(List<T> objs, String msg) {
        System.out.println(msg);
        for (T obj : objs)
            System.out.println(obj);
    }

}
