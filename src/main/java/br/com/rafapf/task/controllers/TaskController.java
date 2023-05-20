package br.com.rafapf.task.controllers;

import br.com.rafapf.task.controllers.dtos.TaskCreateDTO;
import br.com.rafapf.task.controllers.dtos.TaskDTO;
import br.com.rafapf.task.controllers.dtos.TaskUpdateDTO;
import br.com.rafapf.task.models.Task;
import br.com.rafapf.task.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable UUID id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TaskDTO createTask(@RequestBody @Valid TaskCreateDTO taskDTO){
        Task taskSaved = service.createTask(taskDTO.toEntity());
        return TaskDTO.from(taskSaved);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable UUID id, @Valid @RequestBody TaskUpdateDTO taskDTO){
        Task taskUpdated = service.updateTask(id, taskDTO.toEntity());
        return TaskDTO.from(taskUpdated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeTaskById(@PathVariable UUID id){
        service.removeTaskById(id);
    }
}

