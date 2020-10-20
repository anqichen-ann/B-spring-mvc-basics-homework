package com.thoughtworks.capacity.gtb.mvc.Exception;

public class UserNotValidException extends RuntimeException{
    public UserNotValidException(String message){
        super(message);
    }
}
