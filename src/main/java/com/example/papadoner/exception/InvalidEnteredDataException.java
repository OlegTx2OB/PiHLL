package com.example.papadoner.exception;

public class InvalidEnteredDataException extends RuntimeException {
    public InvalidEnteredDataException(String msg) {
        super(msg);
    }
}
