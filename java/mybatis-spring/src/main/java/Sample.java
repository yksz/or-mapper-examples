import java.util.List;

import entity.Book;
import entity.Product;
import service.BookService;
import service.ProductService;
import service.ServiceFactory;

public class Sample {

    public static void main(String[] args) throws Exception {
        Book book = new Book();
        book.setTitle("foo");
        book.setAuthor("bar");

        BookService bookService = ServiceFactory.getBookService();
        bookService.save(book);
        show(bookService.findAll(), "findAll:");
        show(bookService.findByTitle("foo"), "findByTitle:");
        show(bookService.findByAuthor("bar"), "findByAuthor:");


        Product product = new Product();
        product.setName("hoge");
        product.setPrice(100);

        ProductService productService = ServiceFactory.getProductService();
        productService.save(product);
        show(productService.findAll(), "findAll:");
        show(productService.findByName("hoge"), "findByName:");
        show(productService.findByPrice(100), "findByPrice:");
    }

    static <T> void show(List<T> objs, String msg) {
        System.out.println(msg);
        for (T obj : objs)
            System.out.println(obj);
    }

}
