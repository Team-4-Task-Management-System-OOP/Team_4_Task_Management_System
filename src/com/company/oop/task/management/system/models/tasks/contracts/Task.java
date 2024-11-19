package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.contracts.Printable;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.models.teams.contracts.Member;

import java.util.List;

public interface Task extends Commentable, Identifiable, Printable {

    String getTitle();

    String getDescription();

    List<String> getHistory();

    TaskType getTaskType();

    String printImportantInfo();

    String printComments();

    String printLogHistory();

    void historyLogger (String log);

    void addComment(Comment comment);

}
