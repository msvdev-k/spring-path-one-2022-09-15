package org.msvdev.ee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        return new ModelAndView("not_found", HttpStatus.NOT_FOUND);
    }
}