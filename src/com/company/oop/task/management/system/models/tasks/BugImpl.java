package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.enums.BugStatus;
import com.company.oop.task.management.system.models.tasks.enums.Priority;
import com.company.oop.task.management.system.models.tasks.enums.Severity;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.models.teams.contracts.Member;

import java.util.List;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validatePositive;

public class BugImpl extends TaskBase implements Bug {

    // Constants
    public static final String STEPS_ERROR_MESSAGE = "You should enter at least one step for reproducible!";
    public static final int REPRODUCIBLE_STEPS_MIN_LENGTH = 1;

    // Fields
    private Priority priority;
    private List<String> reproducibleSteps;
    private Severity severity;
    private BugStatus bugStatus;
    private TaskType taskType;
    private Member assignee;


    public BugImpl(int id, String title, String description, List<String> reproducibleSteps, Priority priority,
                   Severity severity, BugStatus bugStatus, Member assignee) {
        super(id, title, description);
        setReproducibleSteps(reproducibleSteps);

        // TODO - To consider exception handling ( Maybe in the CommandFactory class )
        this.priority = priority;

        this.severity = severity;

        this.bugStatus = bugStatus;

        this.assignee = assignee;
    }

    private void setReproducibleSteps(List<String> reproducibleSteps) {
        validatePositive(reproducibleSteps.size(), STEPS_ERROR_MESSAGE);
        this.reproducibleSteps = reproducibleSteps;
    }

    @Override
    public List<String> getReproducibleSteps() {
        return reproducibleSteps;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void changePriority(Priority priority) {
        switch (priority) {
            case Priority.HIGH:
                this.priority = Priority.HIGH;
            case Priority.LOW:
                this.priority = Priority.LOW;
            case Priority.MEDIUM:
                this.priority = Priority.MEDIUM;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Severity getSeverityType() {
        return severity;
    }

    @Override
    public BugStatus getBugStatus() {
        return bugStatus;
    }

    @Override
    public void changeBugStatus(BugStatus bugStatus) {
        switch (bugStatus) {
            case BugStatus.ACTIVE:
                this.bugStatus = BugStatus.ACTIVE;
            case BugStatus.DONE:
                this.bugStatus = BugStatus.DONE;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public TaskType getType() {
        return TaskType.BUG;
    }

}
