package br.edu.infnet.teacherservice.exception;

public class InvalidLessonDataException extends RuntimeException {
    public InvalidLessonDataException(String message) {
        super(message);
    }
}