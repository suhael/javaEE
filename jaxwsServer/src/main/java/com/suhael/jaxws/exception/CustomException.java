package com.suhael.jaxws.exception;

public class CustomException extends Exception {
    private String details;
    public CustomException(String details) {
        super(details);
        this.details = details;
    }
    public String getFaultInfo() { return this.details; }
}
