package org.example.exception;

public class StudentSubmittedTheExamException extends RuntimeException{
    public StudentSubmittedTheExamException(String message) {
        super(message);
    }
}