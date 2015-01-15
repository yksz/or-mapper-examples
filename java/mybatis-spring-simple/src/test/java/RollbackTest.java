import static org.junit.Assert.*;

import mapper.BarMapper;
import mapper.FooMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.FooBarService;
import entity.Foo;
import entity.Bar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class RollbackTest {

    @Autowired
    private FooBarService fooBarService;
    @Autowired
    private FooMapper fooMapper;
    @Autowired
    private BarMapper barMapper;

    @Before
    public void setUp() throws Exception {
        fooMapper.dropTable();
        fooMapper.createTable();
        barMapper.dropTable();
        barMapper.createTable();
    }

    @Test
    public void testRollbackForMultiTable() throws Exception {
        // setup:
        Foo foo = new Foo();
        foo.setName("foo");

        Bar bar = new Bar();
        bar.setName("bar");

        // when:
        // then:
        assertEquals(0, fooBarService.findFoo().size());
        assertEquals(0, fooBarService.findBar().size());

        // when:
        try {
            fooBarService.save(foo, bar, new RuntimeException()); // rollback
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then:
        assertEquals(0, fooBarService.findFoo().size());
        assertEquals(0, fooBarService.findBar().size());

        // when:
        try {
            fooBarService.save(foo, bar, new Exception()); // commit
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then:
        assertEquals(1, fooBarService.findFoo().size());
        assertEquals(1, fooBarService.findBar().size());
    }

}
