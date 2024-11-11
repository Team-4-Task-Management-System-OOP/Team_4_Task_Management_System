package com.company.oop.task.management.system.models.teams.contracts;

import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.List;

public interface Member extends Nameble {

    List<Task> getTasks();

    List<String> getActivityHistory();

//    void logActivity(String activity);

}
