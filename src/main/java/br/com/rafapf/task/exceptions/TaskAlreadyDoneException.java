package br.com.rafapf.task.exceptions;

public class TaskAlreadyDoneException extends RuntimeException {
    public TaskAlreadyDoneException(String message) {
        super(message, null, false, false);
    }
}
