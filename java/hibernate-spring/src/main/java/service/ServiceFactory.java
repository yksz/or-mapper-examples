package service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {

    private static final String CONFIG_FILE = "applicationContext.xml";

    private static ApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_FILE);

    public static BookService getBookService() {
        return context.getBean(BookService.class);
    }

}
