package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.*;
import com.company.oop.task.management.system.models.teams.contracts.Member;

import static java.lang.String.format;

public class StoryImpl extends TaskBase implements Story {

    public static final String SET_STORY_ASSIGNEE_SUCCESSFULLY = "Story %s has been successfully assigned to %s. " +
            "Previous assignee was %s.";
    public static final String ALREADY_ASSIGNED_STORY = "Story %s is already assigned to %s.";
    public static final String STORY_PRIORITY_CHANGED = "Story Priority changed from %s to %s successfully.";
    public static final String STORY_PRIORITY_ALREADY_SET = "Story Priority is already set to %s!";
    public static final String STORY_STATUS_CHANGED = "Story Status changed from %s to %s successfully.";
    public static final String STORY_STATUS_ALREADY_SET = "Story Status is already set to %s!";
    public static final String STORY_SIZE_CHANGED = "Story Size changed from %s to %s successfully.";
    public static final String STORY_SIZE_ALREADY_CHANGED = "Story Size is already set to %s!";
    public static final String STORY_PRIORITY_TYPE_CANNOT_BE_EMPTY = "Story's Priority Type cannot be empty!";
    public static final String STORY_STATUS_CANNOT_BE_EMPTY = "Story's Status cannot be empty!";
    public static final String STORY_SIZE_CANNOT_BE_EMPTY = "Story's Size cannot be empty.";
    public static final String ASSIGNEE_CANNOT_BE_EMPTY = "Assignee cannot be empty.";

    private PriorityType storyPriorityType;
    private StorySize size;
    private StoryStatus storyStatus;
    private Member assignee;

    public StoryImpl(int id, String title, String description, PriorityType storyPriorityType,
                     StorySize size) {
        super(id, title, description);
        this.storyPriorityType = storyPriorityType;
        this.size = size;
        this.storyStatus = StoryStatus.NOT_DONE;
        this.assignee = DEFAULT_ASSIGNEE;
    }

    @Override
    public void setAssignee(Member assignee) {
        if (assignee == null) {
            throw new InvalidUserInputException(ASSIGNEE_CANNOT_BE_EMPTY);
        }
        if (!getAssignee().getName().equalsIgnoreCase(assignee.getName())) {
            super.historyLogger(format(SET_STORY_ASSIGNEE_SUCCESSFULLY, getTitle(), assignee.getName(), getAssignee()));
            this.assignee = assignee;
        }
        else{
            throw new InvalidUserInputException(format(ALREADY_ASSIGNED_STORY,
                    getTitle(), getAssignee().getName()));
        }
    }

    @Override
    public PriorityType getPriority() {
        return storyPriorityType;
    }

    @Override
    public StorySize getStorySize() {
        return size;
    }

    @Override
    public StoryStatus getStoryStatus() {
        return storyStatus;
    }

    public Member getAssignee() {
        return assignee;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.STORY;
    }

    //ToDo For enhancement
    private void logChange(String messageTemplate, Object oldValue, Object newValue) {
        super.historyLogger(format(messageTemplate, oldValue, newValue));
    }

    @Override
    public void changePriority(PriorityType priorityNew) {
        if (priorityNew == null) {
            throw new InvalidUserInputException(STORY_PRIORITY_TYPE_CANNOT_BE_EMPTY);
        }
        if (priorityNew != getPriority()){
            super.historyLogger(format(STORY_PRIORITY_CHANGED, getPriority(), priorityNew));
            storyPriorityType = priorityNew;
        }
        else {
            throw new InvalidUserInputException(format(STORY_PRIORITY_ALREADY_SET, getPriority()));
        }
    }

    @Override
    public void changeStoryStatus(StoryStatus storyStatusNew) {
        if (storyStatusNew == null) {
            throw new InvalidUserInputException(STORY_STATUS_CANNOT_BE_EMPTY);
        }
        if (storyStatusNew != getStoryStatus()){
            super.historyLogger(format(STORY_STATUS_CHANGED, getStoryStatus(), storyStatusNew));
            storyStatus = storyStatusNew;
        }
        else {
            throw new InvalidUserInputException(format(STORY_STATUS_ALREADY_SET, getStoryStatus()));
        }
    }

    @Override
    public void changeStorySize(StorySize sizeNew) {
        if (sizeNew == null) {
            throw new InvalidUserInputException(STORY_SIZE_CANNOT_BE_EMPTY);
        }
        if (sizeNew != getStorySize()){
            super.historyLogger(format(STORY_SIZE_CHANGED, getStorySize(), sizeNew));
            size = sizeNew;
        }
        else {
            throw new InvalidUserInputException(format(STORY_SIZE_ALREADY_CHANGED, getStorySize()));
        }
    }

    @Override
    public String printImportantInfo() {
        return format("%s" +
                "Priority Type: %s%n" +
                "Size: %s%n" +
                "Story Status: %s%n" +
                "Assignee: %s%n",
                super.printImportantInfo(), getPriority(), getStorySize(), getStoryStatus(), getAssignee().getName());
    }
}
