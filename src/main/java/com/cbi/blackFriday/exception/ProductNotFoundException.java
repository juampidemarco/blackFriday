package com.cbi.blackFriday.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String menssage){
        super(menssage);
    }

}
