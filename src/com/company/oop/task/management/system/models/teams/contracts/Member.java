package com.company.oop.task.management.system.models.teams.contracts;

import java.util.List;

import com.company.oop.task.management.system.models.tasks.contracts.Assignable;

public interface Member extends Nameable {

    List<Assignable> getTasks();

    List<String> getActivityHistory();

    Team getTeam();

    void setTeam(Team team);

    void addTask(Assignable task);

    void removeTask(Assignable task);

    void addActivityHistory(String history);

}
