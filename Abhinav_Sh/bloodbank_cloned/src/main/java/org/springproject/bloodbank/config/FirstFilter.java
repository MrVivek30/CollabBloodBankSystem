package org.springproject.bloodbank.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter()
public class FirstFilter implements Filter {
    private static final Logger logger = LogManager.getLogger("vehicles");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.fatal("First Filter Init tag");
        logger.info("Inside the init function and this proves that the init funciton of the Filter with the class nameof FirstFilter ahs been intialized");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.fatal("First Filter doFilter ");
        logger.info("This is a message fromt he filter that the soemthing has eben already been filtered");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.fatal("First Filter Distroy  ");
        Filter.super.destroy();
    }
}
