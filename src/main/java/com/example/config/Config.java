package com.example.config;


import com.example.model.Post;
import com.example.model.User;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.*;

@Configuration
@AllArgsConstructor
public class Config {

    private final UserRepository userRepo;

    @Bean
    public CommandLineRunner autoRun() {
        return args -> {

            Post post = new Post(1, "car");
            Post post2 = new Post(2, "book");
            Post post3 = new Post(3, "home");

            userRepo.save(new User(1, "Adam", new Date(), new HashSet<>(Arrays.asList(post, post2))));
            userRepo.save(new User(2, "Eve", new Date(), new HashSet<>(Collections.singletonList(post3))));
            userRepo.save(new User(3, "Jack", new Date()));
        };
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("az"));
        return localeResolver;
    }
}
