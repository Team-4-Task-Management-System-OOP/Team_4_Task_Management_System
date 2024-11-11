package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.*;

public interface Story extends Task {

    Priority getPriority();

    StorySize getSize();

    StoryStatus getStatus();

    String getAssignee();

    TaskType getType();

    void changeStatus(StoryStatus status);

}
