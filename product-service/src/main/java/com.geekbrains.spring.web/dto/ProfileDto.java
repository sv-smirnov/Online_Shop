package com.geekbrains.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    @Schema(description = "Имя пользователя", example = "Bob")
    private String username;
}
