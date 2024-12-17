package com.example.miniprojet.web.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {
    @NotBlank(message="Nom obligatoir")
    private String name  ;
    @NotBlank(message="password obligatoir")
    private String password ;
}
