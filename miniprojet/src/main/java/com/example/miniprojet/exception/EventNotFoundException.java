package com.example.miniprojet.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Long idEvent){
        super ("Event not found with ID : "+ idEvent);
    }
    
}
