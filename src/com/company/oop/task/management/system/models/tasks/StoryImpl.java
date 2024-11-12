package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.*;

public class StoryImpl extends TaskBase implements Story {
    private PriorityType priorityType;
    private StorySize size;
    private StoryStatus status;
    private String assignee;
    private TaskType taskType;

    public StoryImpl(int id, String title, String description, PriorityType priorityType, StorySize size, StoryStatus status, String assignee) {
        super(id, title, description);
        this.priorityType = priorityType;
        this.size = size;
        this.status = status;
        this.assignee = assignee;
    }

    public PriorityType getPriority() {
        return priorityType;
    }

    public StorySize getSize() {
        return size;
    }

    @Override
    public StoryStatus getStatus() {
        return status;
    }

    public String getAssignee() {
        return assignee;
    }

    @Override
    public TaskType getType() {
        return TaskType.STORY;
    }

    @Override
    public void changeStatus(StoryStatus status) {
        if (status.toString() != StoryStatus.DONE.toString()){

        }
    }
}
