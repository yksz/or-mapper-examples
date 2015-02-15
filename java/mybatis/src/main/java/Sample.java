import java.util.List;

import entity.Book;
import entity.Book.Category;
import service.BookService;
import service.BookServiceImpl;

public class Sample {

    public static void main(String[] args) throws Exception {
        Book book = new Book();
        book.setTitle("foo");
        book.setAuthor("bar");

        BookService bookService = new BookServiceImpl();
        System.out.println("save: id=" + bookService.save(book));
        show(bookService.findAll(), "findAll:");
        show(bookService.findByTitle("foo"), "findByTitle:");
        show(bookService.findByAuthor("bar"), "findByAuthor:");
        show(bookService.findByCategory(Category.UNKNOWN), "findByCategory:");
    }

    static <T> void show(List<T> objs, String msg) {
        System.out.println(msg);
        for (T obj : objs)
            System.out.println(obj);
    }

}
