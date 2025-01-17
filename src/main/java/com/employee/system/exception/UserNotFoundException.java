package com.employee.system.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("user not found !");
    }
}
