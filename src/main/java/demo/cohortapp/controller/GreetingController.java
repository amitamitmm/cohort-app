package demo.cohortapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/greetings")
public class GreetingController {

    private final MessageSource messageSource;

    @GetMapping("/greeting")
    public String getGreeting() {
        Locale locale = LocaleContextHolder.getLocale();
        String response =  messageSource.getMessage("greeting.message", null, locale);
        return response;
    }


}
