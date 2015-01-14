package service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServiceFactory {

    private static final String CONFIG_FILE = "application-context.xml";

    private static ApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_FILE);

    public static BlogBookService getBlogBookService() {
        return context.getBean(BlogBookService.class);
    }

}
