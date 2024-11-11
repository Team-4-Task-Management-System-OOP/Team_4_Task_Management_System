package com.company.oop.task.management.system.models.teams;

import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.teams.contracts.Member;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class MemberImpl implements Member {
    private static final int MEMBER_NAME_MIN_LENGTH = 5;
    private static final int MEMBER_NAME_MAX_LENGTH = 15;
    private static final String NAME_ERR = "%s is an invalid member name! " +
            "Name must be a between %d and %d characters!";

    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    public MemberImpl(String name, List<Task> tasks, List<String> activityHistory) {
        this.name = name;
        this.tasks = new ArrayList<>(tasks);
        this.activityHistory = new ArrayList<>(activityHistory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateStringLength(name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH,
                format(NAME_ERR, name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH));
        this.name = name;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }
      //ToDo ima oshte baq metodi i logika tuka

//    @Override
//    public void logActivity(String activity) {
//        activityHistory.add(activity);
//    }


}
