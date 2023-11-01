package com.trackobit.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler(AdminException.class)
    public String adminExceptionHandler(AdminException a, WebRequest req, Model model) {
        MyErrorDetails me = new MyErrorDetails();
        me.setTimeStamp(LocalDateTime.now());
        me.setDetails(req.getDescription(false));
        me.setMessage(a.getMessage());
        model.addAttribute("msg", me);
        return "validation/error";

    }


    @ExceptionHandler(RequestException.class)
    public String requestExceptionHandler(RequestException a, WebRequest req, Model model) {
        MyErrorDetails me = new MyErrorDetails();
        me.setTimeStamp(LocalDateTime.now());
        me.setDetails(req.getDescription(false));
        me.setMessage(a.getMessage());
        model.addAttribute("msg", me);
        return "validation/error";
    }


    @ExceptionHandler(UserException.class)
    public String userExceptionHandler(UserException a, WebRequest req, Model model) {
        MyErrorDetails me = new MyErrorDetails();
        me.setTimeStamp(LocalDateTime.now());
        me.setDetails(req.getDescription(false));
        me.setMessage(a.getMessage());
        model.addAttribute("msg", me);
        return "validation/error";
    }
    @ExceptionHandler(Exception.class)
    public String commonExceptionHandler(Exception a, WebRequest req, Model model) {

        MyErrorDetails me = new MyErrorDetails();
        me.setTimeStamp(LocalDateTime.now());
        me.setDetails(req.getDescription(false));
        me.setMessage(a.getMessage());
        model.addAttribute("msg", me);
        return "validation/error";
    }

}