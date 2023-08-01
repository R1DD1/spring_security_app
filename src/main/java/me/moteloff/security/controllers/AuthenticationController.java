package me.moteloff.security.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.moteloff.security.modules.LoginResponseDTO;
import me.moteloff.security.modules.User;
import me.moteloff.security.services.AuthenticationService;
import org.slf4j.Marker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public User signup(@RequestParam String username, @RequestParam String email, @RequestParam String password){

        return authenticationService.signup(username, email, password);
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestParam String username, @RequestParam String password){
        return authenticationService.login(username, password);
    }
}
