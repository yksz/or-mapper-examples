package gencode;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gencode.entity.Book;
import gencode.mapper.BookMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class GeneratedCodeTest {

    @Autowired
    private BookMapper mapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Before
    public void setup() throws Exception {
        jdbcTemplate.update("DELETE FROM book");
    }

    @Test
    public void testMapper() {
        // setup:
        Book book = new Book();
        book.setId(1);
        book.setTitle("title");
        book.setAuthor("author");

        // when:
        List<Book> initialBooks = mapper.selectAll();

        // then:
        assertEquals(0, initialBooks.size());

        // when:
        Integer insertId = mapper.insertSelective(book);

        // then:
        assertEquals(book.getId(), insertId);

        // when:
        Book insertBook = mapper.selectByPrimaryKey(insertId);

        // then:
        assertEquals(book.getId(),     insertBook.getId());
        assertEquals(book.getTitle(),  insertBook.getTitle());
        assertEquals(book.getAuthor(), insertBook.getAuthor());

        // when:
        book.setTitle("unknown");
        Integer updateId = mapper.updateByPrimaryKeySelective(book);

        // then:
        assertEquals(book.getId(), updateId);

        // when:
        Book updateBook = mapper.selectByPrimaryKey(insertId);
        assertEquals(book.getId(),     updateBook.getId());
        assertEquals(book.getTitle(),  updateBook.getTitle());
        assertEquals(book.getAuthor(), updateBook.getAuthor());

        // when:
        List<Book> finalBooks = mapper.selectAll();

        // then:
        assertEquals(1, finalBooks.size());
    }

}
