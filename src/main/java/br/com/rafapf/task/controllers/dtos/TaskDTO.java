package br.com.rafapf.task.controllers.dtos;

import br.com.rafapf.task.models.Task;

import java.time.LocalDate;

public record TaskDTO(
        String name,
        String description,
        LocalDate deadLine
) {
    public static TaskDTO from(Task task) {
        return new TaskDTO(
                task.getName(),
                task.getDescription(),
                task.getDeadLine()
        );
    }
    public static Task to(TaskDTO taskDTO) {
        return new Task(
                taskDTO.name(),
                taskDTO.description(),
                taskDTO.deadLine()
        );
    }

}
