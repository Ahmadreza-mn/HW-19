package org.example.exception;

public class QuestionNotFoundInExamException extends RuntimeException{
    public QuestionNotFoundInExamException(String message) {
        super(message);
    }
}