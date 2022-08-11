package com.dawid.customers.exception;

public class InvalidNameInput extends RuntimeException{
    public InvalidNameInput(String error) {
        super(error);
    }
}
