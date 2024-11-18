package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.enums.BugStatus;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.BugSeverity;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.models.teams.contracts.Member;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validatePositive;
import static java.lang.String.format;

public class BugImpl extends TaskBase implements Bug {

    public static final String STEPS_ERROR_MESSAGE = "You should enter at least one step for reproducible!";
    public static final String SET_BUG_ASSIGNEE_SUCCESSFULLY = "Bug %s has been successfully assigned to %s. " +
            "Previous assignee was %s.";
    public static final String ALREADY_ASSIGNED_BUG = "Bug %s is already assigned to %s.";
    public static final String BUG_SEVERITY_CHANGED = "Bug Severity changed from %s to %s successfully.";
    public static final String BUG_SEVERITY_ALREADY_SET = "Bug Severity is already set to %s!";
    public static final String BUG_STATUS_CANNOT_BE_EMPTY = "Bug's Priority Type cannot be empty!";
    public static final String BUG_STATUS_CHANGED = "Bug Status changed from %s to %s successfully.";
    public static final String BUG_STATUS_ALREADY_SET = "Bug Status is already set to %s!";
    public static final String BUG_SEVERITY_CANNOT_BE_EMPTY = "Bug's Severity cannot be empty!";
    public static final String BUG_PRIORITY_TYPE_CANNOT_BE_EMPTY = "Bug's Priority Type cannot be empty!";
    public static final String BUG_PRIORITY_CHANGED = "Bug Priority changed from %s to %s successfully.";
    public static final String BUG_PRIORITY = "Bug Priority is already set to %s!";
    public static final String ASSIGNEE_CANNOT_BE_EMPTY = "Assignee cannot be empty.";

    private PriorityType bugPriority;
    private List<String> reproducibleSteps;
    private BugSeverity bugSeverity;
    private BugStatus bugStatus;
    private Member assignee;

    public BugImpl(int id, String title, String description,
                   List<String> reproducibleSteps, PriorityType bugPriority, BugSeverity bugSeverity) {
        super(id, title, description);
        setReproducibleSteps(reproducibleSteps);
        this.bugPriority = bugPriority;
        this.bugSeverity = bugSeverity;
        this.bugStatus = BugStatus.ACTIVE;
        this.assignee = DEFAULT_ASSIGNEE;
    }

    private void setReproducibleSteps(List<String> reproducibleSteps) {
        validatePositive(reproducibleSteps.size(), STEPS_ERROR_MESSAGE);
        this.reproducibleSteps = new ArrayList<>(reproducibleSteps);
    }

    @Override
    public void setAssignee(Member assignee) {
        if (assignee == null) {
            throw new InvalidUserInputException(ASSIGNEE_CANNOT_BE_EMPTY);
        }
        if (!getAssignee().getName().equalsIgnoreCase(assignee.getName())) {
            super.historyLogger(format(SET_BUG_ASSIGNEE_SUCCESSFULLY, getTitle(), assignee.getName(), getAssignee()));
            this.assignee = assignee;
        } else {
            throw new InvalidUserInputException(format(ALREADY_ASSIGNED_BUG,
                    getTitle(), getAssignee().getName()));
        }
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
        return bugPriority;
    }

    @Override
    public BugSeverity getBugSeverity() {
        return bugSeverity;
    }

    @Override
    public BugStatus getBugStatus() {
        return bugStatus;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.BUG;
    }

    //ToDo For enhancement
    private void logChange(String messageTemplate, Object oldValue, Object newValue) {
        super.historyLogger(format(messageTemplate, oldValue, newValue));
    }

    @Override
    public void changeBugStatus(BugStatus bugStatusNew) {
        if (bugStatusNew == null) {
            throw new InvalidUserInputException(BUG_STATUS_CANNOT_BE_EMPTY);
        }
        if (bugStatusNew != getBugStatus()) {
            super.historyLogger(format(BUG_STATUS_CHANGED, getBugStatus(), bugStatusNew));
            bugStatus = bugStatusNew;
        } else {
            throw new InvalidUserInputException(format(BUG_STATUS_ALREADY_SET, getBugStatus()));
        }
    }

    @Override
    public void changeSeverity(BugSeverity bugSeverityNew) {
        if (bugSeverityNew == null) {
            throw new InvalidUserInputException(BUG_SEVERITY_CANNOT_BE_EMPTY);
        }
        if (bugSeverityNew != getBugSeverity()) {
            super.historyLogger(format(BUG_SEVERITY_CHANGED, getBugSeverity(), bugSeverityNew));
            bugSeverity = bugSeverityNew;
        } else {
            throw new InvalidUserInputException(format(BUG_SEVERITY_ALREADY_SET, getBugSeverity()));
        }
    }

    @Override
    public void changePriority(PriorityType priorityNew) {
        if (priorityNew == null) {
            throw new InvalidUserInputException(BUG_PRIORITY_TYPE_CANNOT_BE_EMPTY);
        }
        if (priorityNew != getPriority()) {
            super.historyLogger(format(BUG_PRIORITY_CHANGED, getPriority(), priorityNew));
            bugPriority = priorityNew;
        } else {
            throw new InvalidUserInputException(format(BUG_PRIORITY, getPriority()));
        }
    }

    public String printReproducibleSteps() {
        StringBuilder printReproducibleSteps = new StringBuilder();
        for (int i = 0; i < reproducibleSteps.size(); i++) {
            printReproducibleSteps.append(i + 1).append(". ").append(reproducibleSteps.get(i));
            if (i < reproducibleSteps.size() - 1) {
                printReproducibleSteps.append(System.lineSeparator());
            }
        }
        return printReproducibleSteps.toString();
    }

    @Override
    public String printImportantInfo() {
        return format("%s" +
                        "Priority Type: %s%n" +
                        "Severity: %s%n" +
                        "Bug Status: %s%n" +
                        "Assignee: %s%n" +
                        "---Reproducible steps---%n%s%n",
                super.printImportantInfo(), getPriority(), getBugSeverity(),
                getBugStatus(), getAssignee().getName(), printReproducibleSteps());
    }
}
