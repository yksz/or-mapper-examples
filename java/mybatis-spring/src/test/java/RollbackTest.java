import static org.junit.Assert.*;

import org.junit.Test;

import service.BlogBookService;
import service.TestServiceFactory;
import entity.Blog;
import entity.Book;

public class RollbackTest {

    @Test
    public void testRollback() throws Exception {
        // setup:
        Blog blog = new Blog();
        blog.setTitle("blog title");
        blog.setAuthor("blog author");

        Book book = new Book();
        book.setTitle("book title");
        book.setAuthor("book author");

        // when:
        BlogBookService service = TestServiceFactory.getBlogBookService();

        // then:
        assertEquals(0, service.findAllBlogs().size());
        assertEquals(0, service.findAllBooks().size());

        // when:
        try {
            service.save(blog, book, 1); // rollback
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        // then:
        assertEquals(0, service.findAllBlogs().size());
        assertEquals(0, service.findAllBooks().size());

        // when:
        try {
            service.save(blog, book, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then:
        assertEquals(1, service.findAllBlogs().size());
        assertEquals(1, service.findAllBooks().size());
    }

}
