package com.dentalmoovi.webpage.exceptions;

public class DataExistException extends RuntimeException{
    public DataExistException(String message){
        super(message);
    }
}