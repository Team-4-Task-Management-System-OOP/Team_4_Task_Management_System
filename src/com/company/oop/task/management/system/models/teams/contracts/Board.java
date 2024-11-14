package com.company.oop.task.management.system.models.teams.contracts;

import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.List;

public interface Board extends Nameable {

    List<Task> getTasks();

    List<String> getHistory();

    void addTask(Task task);

    void addActivityHistory(String activity);

}
