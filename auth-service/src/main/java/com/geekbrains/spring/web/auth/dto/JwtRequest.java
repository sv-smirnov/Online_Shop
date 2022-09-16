package com.geekbrains.spring.web.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JwtRequest {
    @Schema(description = "Имя пользователя", example = "Bob")
    private String username;
    @Schema(description = "Пароль", example = "100")
    private String password;
}
