package br.edu.infnet.lessonservice.exception;

public class InvalidLessonDataException extends RuntimeException {
    public InvalidLessonDataException(String message) {
        super(message);
    }
}