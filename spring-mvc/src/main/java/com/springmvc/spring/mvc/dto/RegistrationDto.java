package com.springmvc.spring.mvc.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private long id;
    @NotEmpty(message = "username should not be empty")
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

}
