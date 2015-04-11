package org.example.datasource;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private static final String CONFIG_FILE = "org/example/datasource/mybatis-config.xml";

    @Override
    public void start(BundleContext context) throws Exception {
        SessionManager.init(getClass().getClassLoader(), CONFIG_FILE);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }

}
