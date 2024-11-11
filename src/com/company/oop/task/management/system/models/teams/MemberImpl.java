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
    private static final int MEMBER_NAME_MIN_LENGTH = 5;
    private static final int MEMBER_NAME_MAX_LENGTH = 15;

    private static final String NAME_ERR = "%s is an invalid member name! " +
            "Name must be a between %d and %d characters!";

    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateStringLength(name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH,
                format(NAME_ERR, name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH));
        // TODO - Consider checking for unique name in the CommandFactory (eventually)
        this.name = name;

        @Override
        public List<Task> getTasks () {
            return new ArrayList<>(tasks);
        }

        @Override
        public List<String> getActivityHistory () {
            return new ArrayList<>(activityHistory);
        }

        @Override
        public void addTask (Task task){
            tasks.add(task);
            addActivity("The following task has been assigned: " + task.getTitle());
        }

        @Override
        public void addActivity (String activity){
            activityHistory.add(activity);
        }
    }
}
