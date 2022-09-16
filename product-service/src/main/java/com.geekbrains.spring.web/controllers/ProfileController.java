package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.dto.ProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profile")
@Tag(name = "Профиль", description = "Контроллер профиля пользователя")
public class ProfileController {
    @Operation(description = "Получить информацию о пользователе")
    @GetMapping
    public ProfileDto getCurrentUserInfo(@RequestHeader String username) {
        return new ProfileDto(username);
    }
}
