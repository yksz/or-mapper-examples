package service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {

    private static final String CONFIG_FILE = "spring/context.xml";

    private static ApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_FILE);

    public static LocalService getLocalService() {
        return context.getBean(LocalService.class);
    }

    public static RemoteService getRemoteService() {
        return context.getBean(RemoteService.class);
    }

}
