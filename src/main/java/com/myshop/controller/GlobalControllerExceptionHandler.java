package com.myshop.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.slf4j.LoggerFactory.getLogger;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger log = getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception
    {
        log.error("exception at request" + request.getRequestURL());
        ModelAndView modelAndView = new ModelAndView("not_enough_items");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }
}
