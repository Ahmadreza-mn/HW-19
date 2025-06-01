package org.example.exception;

public class NotCompletedExamException extends RuntimeException{
    public NotCompletedExamException(String message) {
        super(message);
    }
}