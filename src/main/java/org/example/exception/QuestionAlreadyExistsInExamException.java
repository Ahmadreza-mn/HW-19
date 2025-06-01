package org.example.exception;

public class QuestionAlreadyExistsInExamException extends RuntimeException{
    public QuestionAlreadyExistsInExamException(String message) {
        super(message);
    }
}