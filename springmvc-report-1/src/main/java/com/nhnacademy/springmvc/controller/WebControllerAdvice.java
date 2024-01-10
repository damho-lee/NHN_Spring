package com.nhnacademy.springmvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler
    public String handleException(Exception exception, Model model) {
        model.addAttribute("exception", exception);

        return "error";
    }
}
