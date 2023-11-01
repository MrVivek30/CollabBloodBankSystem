package com.trackobit.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//2nd approach-----------------web.xml alternative
//spring is taking control and creating the ds object for us
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class arr[] = {AppConfig.class};
        return arr;
    }

    @Override
    protected String[] getServletMappings() {
        String arr[] = {"/"};
        return arr;
    }
}
