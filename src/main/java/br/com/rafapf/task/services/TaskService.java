package br.com.rafapf.task.services;

import br.com.rafapf.task.exceptions.TaskAlreadyDoneException;
import br.com.rafapf.task.exceptions.TaskAlreadyExistsException;
import br.com.rafapf.task.exceptions.TaskNotFoundException;
import br.com.rafapf.task.models.Task;
import br.com.rafapf.task.repositories.TaskRepository;
import br.com.rafapf.task.utils.enums.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    public Task createTask(Task task){
        Optional<Task> taskSave = repository.findByName(task.getName());
        if(taskSave.isPresent() && !taskSave.get().getStatus().equals(TaskStatus.DONE)){
            throw new TaskAlreadyExistsException("This task already exists and is not done");
        }
        task.setLate(LocalDate.now().isAfter(task.getDeadLine()));

        repository.save(task);
        return task;
    }

    public Task updateTask(UUID id, Task newTask){
        var taskSave = findById(id);
        Optional<Task> taskAlreadySaved = repository.findByName(newTask.getName());
        if(taskAlreadySaved.isPresent() && taskAlreadySaved.get().getStatus().equals(TaskStatus.DONE)){
            throw new TaskAlreadyDoneException("This task already done");
        }
        taskSave.setName(newTask.getName());
        taskSave.setDescription(newTask.getDescription());
        taskSave.setStatus(newTask.getStatus());
        taskSave.setDeadLine(newTask.getDeadLine());
        taskSave.setLate(LocalDate.now().isAfter(newTask.getDeadLine()));
        repository.save(taskSave);

        return taskSave;
    }

    public void removeTaskById(UUID id){
        var task = findById(id);
        if(task.getStatus().equals(TaskStatus.DONE)){
            throw new TaskAlreadyDoneException("This task is already done");
        }
        repository.delete(task);
    }

}
