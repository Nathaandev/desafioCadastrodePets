package com.example.cadastropet.Exceptions;

public class InvalidPetIDException extends RuntimeException {
    public InvalidPetIDException(String message) {
        super(message);
    }
}
