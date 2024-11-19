package com.company.oop.task.management.system.models.teams.contracts;

import com.company.oop.task.management.system.models.contracts.Printable;
import com.company.oop.task.management.system.models.tasks.contracts.Assignable;
import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.List;

public interface Board extends Nameable, Printable {

    List<Task> getTasks();

    List<Assignable> getAssignableTasks();

    List<String> getHistory();

    void addTask(Task task);

    void addTask(Assignable task);

    void removeTask(Task task);

    void removeTask(Assignable task);

    void addActivityHistory(String history);

    String printHistory();

    String printTasks();
}
