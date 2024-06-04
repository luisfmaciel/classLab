package br.edu.infnet.classlab.exception;

public class InvalidLessonDataException extends RuntimeException {
    public InvalidLessonDataException(String message) {
        super(message);
    }
}