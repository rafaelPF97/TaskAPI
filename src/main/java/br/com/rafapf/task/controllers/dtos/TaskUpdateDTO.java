package br.com.rafapf.task.controllers.dtos;

import br.com.rafapf.task.models.Task;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Optional;

public record TaskUpdateDTO(
    @NotBlank(message = "Name is required")
    String name,
    @NotBlank(message = "Description is required")
    String description,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Optional<LocalDate> deadLine
) {
    public Task toEntity() {
        LocalDate updatedDeadLine = deadLine.orElseGet(LocalDate::now);
        return new Task(
            this.name,
            this.description,
            updatedDeadLine
        );

    }
}
