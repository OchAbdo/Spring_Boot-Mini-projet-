package com.example.miniprojet.web.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotBlank
    private String nom ;
    @Email(message = "Email is not valid (xyz@email.com)")
    @NotBlank
    private String email;
    @NotBlank
    private String telephone ;
    @NotBlank
    private String paiment;
    
}
