package br.com.rafapf.task.models;

import br.com.rafapf.task.utils.enums.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_task")
public class Task {
    @Id
    @Column(name = "cd_task")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nm_task")
    private String name;
    @Column(name = "st_task")
    private TaskStatus status = TaskStatus.TODO;
    @Column(name = "ds_task")
    private String description;
    @Column(name = "dt_task")
    private LocalDate deadLine;
    @Column(name = "fl_task")
    private boolean isLate;

    public Task(UUID id, String name, String description, LocalDate deadLine) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadLine = deadLine;
    }

    public Task(String name, String description, LocalDate deadLine) {
        this.name = name;
        this.description = description;
        this.deadLine = deadLine;
    }

    public Task() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }
}
