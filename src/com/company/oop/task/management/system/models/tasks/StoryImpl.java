package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.*;
import com.company.oop.task.management.system.models.teams.contracts.Member;

public class StoryImpl extends TaskBase implements Story {
    private PriorityType priorityType;
    private StorySize size;
    private StoryStatus status;
    private Member assignee;
    private TaskType taskType;

    public StoryImpl(int id, String title, String description, PriorityType priorityType, StorySize size, StoryStatus status, Member assignee) {
        super(id, title, description);
        this.priorityType = priorityType;
        this.size = size;
        this.status = status;
        this.assignee = assignee;
    }

    @Override
    public PriorityType getPriority() {
        return priorityType;
    }
    //ToDo
    @Override
    public void changePriority(PriorityType priority) {

    }
    //ToDo
    @Override
    public void setAssignee(Member assignee) {

    }
    //ToDo
    @Override
    public StorySize getSize() {
        return size;
    }

    @Override
    public StoryStatus getStatus() {
        return status;
    }

    public Member getAssignee() {
        return assignee;
    }

    @Override
    public TaskType getType() {
        return TaskType.STORY;
    }

    //ToDo changeStatus implementation
    @Override
    public void changeStatus(StoryStatus status) {
        if (status.toString() != StoryStatus.DONE.toString()){

        }
    }
    //ToDo
    @Override
    public void changeSize(StorySize size) {

    }

    //ToDo Possibly a printing method
}
