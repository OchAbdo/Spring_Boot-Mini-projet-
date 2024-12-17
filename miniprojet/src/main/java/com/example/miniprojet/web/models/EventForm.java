package com.example.miniprojet.web.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EventForm {

    @NotBlank(message = "obligatoire")
    private String titre ;
    @NotBlank(message = "obligatoire")
    private String description;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotBlank(message = "obligatoire")
    private String lieu;
    @Min(10)
    private int nbplace;
    @Min(1)
    private int prix;
    @NotBlank(message = "obligatoire")
    private String categorie;
    private String image ;
    
}
