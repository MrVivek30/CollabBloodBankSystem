package org.springproject.bloodbank.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLoggerContextFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FIrstInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger("vehicles");
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.fatal("This is the First Interceptor");
        logger.info("The Method its intending" + request.getMethod());
        logger.info("The Requested URI "+ request.getRequestURI());
        logger.info("Get the Servlet path "+request.getServletPath());

        if(handler instanceof HandlerMethod){
            Class<?> controllerClass=((HandlerMethod) handler).getBeanType();
            String methodName=((HandlerMethod) handler).getMethod().getName();
            logger.info("Controller Name "+controllerClass.getName());
            logger.info("Get the name of the method "+methodName);
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if(handler instanceof HandlerMethod){
            Class<?> controllerClass=((HandlerMethod) handler).getBeanType();
            String methodName=((HandlerMethod) handler).getMethod().getName();
            logger.info("Controller Name "+controllerClass.getName());
            logger.info("Get the name of the method "+methodName);
        }
        logger.info("Inside the postHandle");
    }

     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.info("After the completion doing some of the final touches");
        if(ex !=null)
            logger.info("An  Exception has occured that says - > "+ex.getMessage());
        else
            logger.info("No exception have occured");
    }
}
