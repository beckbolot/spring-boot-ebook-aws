package com.beck.springbootebookaws.exceptions;

public class NoSuchElementException extends RuntimeException {

    public NoSuchElementException(Class<?> clazz,long id) {
        super(String.format("Entity %s with id %d not found",clazz.getSimpleName(),id));
    }
}
