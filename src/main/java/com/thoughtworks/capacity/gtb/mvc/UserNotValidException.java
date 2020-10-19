package com.thoughtworks.capacity.gtb.mvc;

public class UserNotValidException extends RuntimeException{
    public UserNotValidException(String message){
        super(message);
    }
}
