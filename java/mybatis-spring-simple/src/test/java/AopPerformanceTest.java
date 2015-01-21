import mapper.BarMapper;
import mapper.FooMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Bar;
import entity.Foo;

import service.FooBarService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class AopPerformanceTest {

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
    public void testPerformance() {
        for (int i = 1; i <= 3; i++) {
            System.err.println(i + ":");
            measurePerformance();
        }
    }

    private void measurePerformance() {
        Foo foo = new Foo();
        foo.setName("foo");
        Bar bar = new Bar();
        bar.setName("bar");
        {
            long begin = System.nanoTime();
            try {
                fooBarService.save(foo, bar, new RuntimeException());
            } catch (Exception e) {
            }
            long end = System.nanoTime();
            System.err.println("- Declarative transaction with annotation: " + (end - begin) / 1000000.0 + "[ms]");
        }
        {
            long begin = System.nanoTime();
            try {
                fooBarService.saveWithProgrammaticTransaction(foo, bar, new RuntimeException());
            } catch (Exception e) {
            }
            long end = System.nanoTime();
            System.err.println("- Programmatic transaction: " + (end - begin) / 1000000.0 + "[ms]");
        }
    }

}
