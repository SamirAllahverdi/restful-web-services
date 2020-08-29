package com.example.controller;


import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloWorldController {

    private final MessageSource messageSource;


    @GetMapping(path = "/internationalized")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning.message", null,
                LocaleContextHolder.getLocale());
    }


}
