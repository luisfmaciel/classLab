package br.edu.infnet.teacherservice.exception;

public class InvalidTeacherDataException extends RuntimeException {
    public InvalidTeacherDataException(String message) {
        super(message);
    }
}
