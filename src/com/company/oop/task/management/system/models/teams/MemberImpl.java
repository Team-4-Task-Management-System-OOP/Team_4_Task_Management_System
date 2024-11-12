
package com.company.oop.task.management.system.models.teams;

import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.teams.contracts.Member;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.models.teams.TeamImpl.NAME_MAX_LENGTH;
import static com.company.oop.task.management.system.models.teams.TeamImpl.NAME_MIN_LENGTH;
import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class MemberImpl implements Member {

    // Constants
    private static final String MEMBER_NAME_LENGTH_ERR = format(
            "Member must be between %d and %d!",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);

    // Fields
    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        this.name = name;
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    private void setName(String name) {
        validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, MEMBER_NAME_LENGTH_ERR);
        // TODO - Consider checking for unique name in the CommandFactory (eventually)
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public List<String> getActivityHistory() {
        return activityHistory;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        addActivity("The following task has been assigned: " + task.getTitle());
    }

    @Override
    public void addActivity(String activity) {
        activityHistory.add(activity);
    }
}
