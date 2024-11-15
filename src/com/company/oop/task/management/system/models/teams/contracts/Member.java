package com.company.oop.task.management.system.models.teams.contracts;

import java.util.List;

import com.company.oop.task.management.system.models.contracts.Printable;
import com.company.oop.task.management.system.models.tasks.contracts.Assignable;
import com.company.oop.task.management.system.models.tasks.contracts.Task;

public interface Member extends Nameable, Printable {

    List<Task> getTasks();

    List<String> getActivityHistory();

    Team getTeam();

    void setTeam(Team team);

    void addTask(Task task);

    void removeTask(Task task);

    void addActivityHistory(String history);

    String printHistory();

}
