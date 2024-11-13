package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.enums.BugStatus;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.BugSeverity;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.models.teams.contracts.Member;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validatePositive;

public class BugImpl extends TaskBase implements Bug {

    // Constants
    public static final String STEPS_ERROR_MESSAGE = "You should enter at least one step for reproducible!";
    public static final int REPRODUCIBLE_STEPS_MIN_LENGTH = 1;

    // Fields
    private PriorityType priority;
    private List<String> reproducibleSteps;
    private BugSeverity bugSeverity;
    private BugStatus bugStatus;
    private TaskType taskType;
    private Member assignee;


    public BugImpl(int id, String title, String description, List<String> reproducibleSteps, PriorityType priority,
                   BugSeverity bugSeverity, BugStatus bugStatus, Member assignee) {
        super(id, title, description);
        setReproducibleSteps(reproducibleSteps);
        // TODO - To consider exception handling ( Maybe in the CommandFactory class )
        this.priority = priority;
        this.bugSeverity = bugSeverity;
        this.bugStatus = bugStatus;
        setAssignee(assignee);
    }

    private void setReproducibleSteps(List<String> reproducibleSteps) {
        validatePositive(reproducibleSteps.size(), STEPS_ERROR_MESSAGE);
        this.reproducibleSteps = new ArrayList<>(reproducibleSteps);
    }

    @Override
    public List<String> getReproducibleSteps() {
        return new ArrayList<>(reproducibleSteps);
    }

    @Override
    public Member getAssignee() {
        return assignee;
    }

    @Override
    public PriorityType getPriority() {
        return priority;
    }
    //ToDo
    @Override
    public void changePriority(PriorityType priority) {
        switch (priority) {
            case HIGH:
                this.priority = PriorityType.HIGH;
            case LOW:
                this.priority = PriorityType.LOW;
            case MEDIUM:
                this.priority = PriorityType.MEDIUM;
            default:
                throw new IllegalArgumentException();
        }
    }
    //ToDo
    @Override
    public void setAssignee(Member assignee) {
        this.assignee = assignee;
    }
    //ToDo
    @Override
    public BugSeverity getSeverityType() {
        return bugSeverity;
    }

    @Override
    public BugStatus getBugStatus() {
        return bugStatus;
    }

    @Override
    public void changeBugStatus(BugStatus bugStatus) {
        switch (bugStatus) {
            case ACTIVE:
                this.bugStatus = BugStatus.ACTIVE;
            case DONE:
                this.bugStatus = BugStatus.DONE;
            default:
                throw new IllegalArgumentException();
        }
    }
    //ToDo
    @Override
    public void changeSeverity(BugSeverity bugSeverity) {

    }

    @Override
    public TaskType getType() {
        return TaskType.BUG;
    }
//ToDo Possibly a printing method
}
