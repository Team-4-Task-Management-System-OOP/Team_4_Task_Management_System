package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.TaskType;

import java.util.List;

public interface Task extends Commentable, Identifable {

    String getTitle();

    String getDescription();

    List<String> getHistory();

    TaskType getType();

    String printInfo();

    void historyLogger (String log);

    void addComment(Comment comment);



}
