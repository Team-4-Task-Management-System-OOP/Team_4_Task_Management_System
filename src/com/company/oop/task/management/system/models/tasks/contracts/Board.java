package com.company.oop.task.management.system.models.tasks.contracts;

import java.util.List;

public interface Board {

    String getName();

    List<String> getTasks();

    List<String> getActivityHistory();

    void AddActivity(String activity);

    void addTask(String task);

}
