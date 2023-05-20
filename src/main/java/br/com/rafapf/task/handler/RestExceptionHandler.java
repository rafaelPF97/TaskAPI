package br.com.rafapf.task.handler;

import br.com.rafapf.task.exceptions.ErrorDTO;
import br.com.rafapf.task.exceptions.TaskAlreadyDoneException;
import br.com.rafapf.task.exceptions.TaskAlreadyExistsException;
import br.com.rafapf.task.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleTaskNotFoundException(TaskNotFoundException exception) {
        return new ErrorDTO(exception.getMessage(),"404");
    }

    @ExceptionHandler(TaskAlreadyDoneException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleTaskAlreadyDoneException(TaskAlreadyDoneException exception) {
        return new ErrorDTO(exception.getMessage(),"400");
    }

    @ExceptionHandler(TaskAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleTaskAlreadyExistsException(TaskAlreadyExistsException exception) {
        return new ErrorDTO(exception.getMessage(),"400");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExcption(MethodArgumentNotValidException exception) {
        Map<String,String> errors = new HashMap<>();
        String status = "status";
        String title = "title";
        String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        String reason = HttpStatus.BAD_REQUEST.getReasonPhrase();
        errors.put(title, reason);
        errors.put(status, code);
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        }
        return errors;
    }
}
