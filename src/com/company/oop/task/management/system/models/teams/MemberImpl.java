package com.company.oop.task.management.system.models.teams;

import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.List;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class MemberImpl {
    private static final int MEMBER_NAME_MIN_LENGTH = 5;
    private static final int MEMBER_NAME_MAX_LENGTH = 15;
    private static final String NAME_ERR = "%s is an invalid member name! " +
            "Name must be a between %d and %d characters!";

    private String name;
    private List<Task> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateStringLength(name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH,
                format(NAME_ERR, name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH));
        this.name = name;
    }
}
