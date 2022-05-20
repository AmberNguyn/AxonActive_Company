package com.axonactive.company.exception;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
