package org.springproject.bloodbank.exceptions;

import org.apache.jasper.JasperException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.LoginException;
import java.util.NoSuchElementException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvisor {
    private static final Logger logger = LogManager.getLogger("vehicles");
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String exceptionHandlerNull(Exception ex, Model model) {
        logger.info("An Exception has occured " + ex.getMessage());
        return "something_went_wrong";
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NoSuchElementException.class,NullPointerException.class})
    public String exceptionHandlerNSE(Exception ex, Model model) {
        logger.info("An Exception has occured No such Element" + ex.getMessage());
        model.addAttribute("error","Invalid request");
        return "something_went_wrong";
    }
}
