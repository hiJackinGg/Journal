package com.mycompany.journal.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ApplicationConfig {

    private static final String SPRING_DAO_CONFIG_XML = "applicationContext.xml";

    private static ApplicationContext springAppContext;

    ApplicationConfig() {}


    public static ApplicationContext getSpringApplicationContext() {
        if (springAppContext == null) {
            springAppContext = getSpringApplicationContext(SPRING_DAO_CONFIG_XML);
        }
        return springAppContext;
    }

    public static ApplicationContext getSpringApplicationContext(String contextPath) {
        springAppContext = new ClassPathXmlApplicationContext(contextPath);
        return springAppContext;
    }

}
