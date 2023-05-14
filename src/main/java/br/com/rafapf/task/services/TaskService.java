package br.com.rafapf.task.services;

import br.com.rafapf.task.models.Task;
import br.com.rafapf.task.repositories.TaskRepository;
import br.com.rafapf.task.utils.enums.TaskStatus;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task createTask(Task task){
        Optional<Task> taskSave = repository.findByName(task.getName());
        if(taskSave.isPresent() && !taskSave.get().getStatus().equals(TaskStatus.DONE)){
            throw new RuntimeException("This task already exists and is not done");
        }
        task.setStatus(TaskStatus.TODO);
        repository.save(task);
        return task;
    }

    public Task updateTask(UUID id, Task newTask){
        var taskSave = findById(id);
        Optional<Task> taskAlreadySaved = repository.findByName(newTask.getName());
        if(taskAlreadySaved.isPresent() && taskAlreadySaved.get().getStatus().equals(TaskStatus.DONE)){
            throw new RuntimeException("This task already done");
        }
        taskSave.setName(newTask.getName());
        taskSave.setDescription(newTask.getDescription());
        taskSave.setStatus(newTask.getStatus());
        taskSave.setDeadLine(newTask.getDeadLine());
        repository.save(taskSave);
        return taskSave;
    }

    public void removeTaskById(UUID id){
        var task = findById(id);
        if(task.getStatus().equals(TaskStatus.DONE)){
            throw new RuntimeException("This task is already done");
        }
        repository.delete(task);
    }
}
