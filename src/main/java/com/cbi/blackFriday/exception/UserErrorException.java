package com.cbi.blackFriday.exception;

public class UserErrorException extends RuntimeException{
    public UserErrorException(String menssage){
        super(menssage);
    }
}
