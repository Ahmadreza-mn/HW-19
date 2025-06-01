package org.example.exception;

public class StudentCompletedTheExamException extends RuntimeException{
    public StudentCompletedTheExamException(String message) {
        super(message);
    }
}