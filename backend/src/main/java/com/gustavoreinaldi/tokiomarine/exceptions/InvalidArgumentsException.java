package com.gustavoreinaldi.tokiomarine.exceptions;

public class InvalidArgumentsException extends RuntimeException{
    public InvalidArgumentsException() {
        super();
    }

    public InvalidArgumentsException(String message) {
        super(message);
    }
}
