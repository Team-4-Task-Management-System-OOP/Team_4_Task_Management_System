package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.StorySize;
import com.company.oop.task.management.system.models.tasks.enums.StoryStatus;

public interface Story extends Task, Assignable {

    StoryStatus getStatus();

    StorySize getSize();

    void changeStatus(StoryStatus status);

    void changeSize(StorySize size);

}
