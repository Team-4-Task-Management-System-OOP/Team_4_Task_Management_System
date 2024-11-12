package com.company.oop.task.management.system.models.teams.contracts;
import java.util.List;
import com.company.oop.task.management.system.models.tasks.contracts.Task;

public interface Member {
    String getName();

    List<Task> getTasks();

    List<String> getActivityHistory();

    void addTask(Task task);

    void addActivity(String activity);
}
