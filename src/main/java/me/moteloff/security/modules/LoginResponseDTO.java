package me.moteloff.security.modules;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private User user;
    private String jwt;
}
