package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.StorySize;
import com.company.oop.task.management.system.models.tasks.enums.StoryStatus;

public interface Story extends Task, Assignable {

    StoryStatus getStoryStatus();

    StorySize getStorySize();

    void changeStoryStatus(StoryStatus status);

    void changeStorySize(StorySize size);

}
