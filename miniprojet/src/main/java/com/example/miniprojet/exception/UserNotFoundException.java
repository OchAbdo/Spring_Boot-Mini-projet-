package com.example.miniprojet.exception;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(Long idUSer){
        super ("User not found with ID : "+ idUSer);
    }
    
}
