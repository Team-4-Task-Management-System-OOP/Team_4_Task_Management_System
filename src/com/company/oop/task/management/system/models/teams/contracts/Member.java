package com.company.oop.task.management.system.models.teams.contracts;

import java.util.List;

import com.company.oop.task.management.system.models.tasks.contracts.Task;

public interface Member extends Nameable {

    List<Task> getTasks();

    List<String> getActivityHistory();

    Team getTeam();

    Team setTeam();

    void addTask(Task task);

    void removeTask(Task task);

    void addActivity(String activity);

}
