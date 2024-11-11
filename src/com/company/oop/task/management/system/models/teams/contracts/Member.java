package com.company.oop.task.management.system.models.teams.contracts;

import java.util.List;

import com.company.oop.task.management.system.models.tasks.contracts.Task;

import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.List;

public interface Member extends Nameble {

    List<Task> getTasks();

    List<String> getActivityHistory();

    void addTask(Task task);

    void addActivity(String activity);

}
