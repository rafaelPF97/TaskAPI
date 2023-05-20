package br.com.rafapf.task.controllers.dtos;

import br.com.rafapf.task.models.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record TaskCreateDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Description is required")
        String description,
        @NotNull(message = "Deadline is required")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate deadLine
) {
    public Task toEntity() {
        return new Task(
                this.name,
                this.description,
                this.deadLine
        );

    }
}
