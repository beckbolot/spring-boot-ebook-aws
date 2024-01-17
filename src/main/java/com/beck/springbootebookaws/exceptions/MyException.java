package com.beck.springbootebookaws.exceptions;

public class MyException extends RuntimeException{

    private String HttpStatus;
    private String message;

    public MyException(String message, Throwable cause, String httpStatus, String message1) {
        super(message, cause);
        HttpStatus = httpStatus;
        this.message = message1;
    }
}
