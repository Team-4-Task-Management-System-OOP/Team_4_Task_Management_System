package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.*;

import javax.xml.stream.events.Comment;
import java.util.List;

public class StoryImpl extends TaskBase implements Story {
    private Priority priority;
    private StorySize size;
    private StoryStatus status;
    private String assignee;
    private TaskType taskType;

    public StoryImpl(int id, String title, String description, Priority priority, StorySize size, StoryStatus status, String assignee) {
        super(id, title, description);
        this.priority = priority;
        this.size = size;
        this.status = status;
        this.assignee = assignee;
    }

    public Priority getPriority() {
        return priority;
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
