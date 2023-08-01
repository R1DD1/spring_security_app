package me.moteloff.security.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
@Async
@Slf4j
public class PageController {


    @GetMapping("/feed")
    public CompletableFuture<String> feedPage() {
        return CompletableFuture.completedFuture("feed");
    }

    @GetMapping("/auth/signup")
    public CompletableFuture<String> signupPage() {
        return CompletableFuture.completedFuture("signup");
    }

    @GetMapping("/auth/login")
    public CompletableFuture<String> loginPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {

            log.info(authentication.getName());
        } else {
            log.info("not");
        }
        return CompletableFuture.completedFuture("login");
    }
}
