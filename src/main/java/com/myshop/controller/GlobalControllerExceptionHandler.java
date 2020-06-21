package com.myshop.controller;

import com.myshop.util.exception.NotEnoughProductInStorage;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import static org.slf4j.LoggerFactory.getLogger;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger log = getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = NotEnoughProductInStorage.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception
    {
        log.error("exception at request" + request.getRequestURL());
        ModelAndView modelAndView = new ModelAndView("not_enough_items");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
        ModelAndView mav = new ModelAndView("404");
        mav.addObject("exception", e);
        return mav;
    }
}
