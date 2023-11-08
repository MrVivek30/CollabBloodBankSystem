package org.springproject.bloodbank.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final Logger logger = LogManager.getLogger("vehicles");

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new FirstFilter()};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        logger.info("Configured the dispatcher servlet configuration class");

        return new Class[]{
                ConfigurationClass.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        logger.info("Configured the root servlet mapping");
        String str[] = {"/bloodbank/*"};
        return str;
    }
}
