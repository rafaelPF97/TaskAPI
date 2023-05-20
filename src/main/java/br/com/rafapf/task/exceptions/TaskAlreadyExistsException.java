package br.com.rafapf.task.exceptions;

public class TaskAlreadyExistsException extends RuntimeException {
    public TaskAlreadyExistsException(String message) {
        super(message, null, false, false);
    }
}
