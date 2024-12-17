package com.example.miniprojet.exception.model;

import java.beans.Transient;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ErrorForm(  
    LocalDateTime timeStamp,
    String message,
    @Transient
    String errorAuthor,
    int httpStatus) {
} 
    

