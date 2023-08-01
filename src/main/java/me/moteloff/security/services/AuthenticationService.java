package me.moteloff.security.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.moteloff.security.modules.LoginResponseDTO;
import me.moteloff.security.modules.User;
import me.moteloff.security.repositrories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final UserService userService;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(String username, String email, String password) {
        User user = new User(0, username, email, UUID.randomUUID(),  passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    // Метод для выполнения аутентификации пользователя
    public LoginResponseDTO login(String username, String password) {
        try {
            UserDetails userDetails = userService.loadUserByUsername(username);

            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                Authentication authenticated = authenticationManager.authenticate(authentication);

                String token = tokenService.generateJwt(authenticated);

                return new LoginResponseDTO(userRepository.findByUsername(username).orElse(null), token);
            } else {
                return new LoginResponseDTO(null, "");
            }
        } catch (UsernameNotFoundException e) {
            return new LoginResponseDTO(null, "");
        }
    }
}
