package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.FeedbackStatus;
import com.company.oop.task.management.system.models.tasks.enums.StoryStatus;

public interface Story extends Task {

    StoryStatus getStatus();

    void changeStatus(StoryStatus status);

}
