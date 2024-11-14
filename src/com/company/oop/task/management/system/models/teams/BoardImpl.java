package com.company.oop.task.management.system.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.teams.contracts.Board;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class BoardImpl implements Board {

    // Constants
    public static final int BOARD_NAME_MIN_LENGTH = 5;
    public static final int BOARD_NAME_MAX_LENGTH = 10;
    private static final String BOARD_NAME_LENGTH_ERR = format(
            "Board name must be between %d and %d!",
            BOARD_NAME_MIN_LENGTH,
            BOARD_NAME_MAX_LENGTH);
    private static final String ADD_TASK_SUCCESSFUL_MESSAGE = "The following task has been added: ";

    private static final String ACTIVITY_NULL_MESSAGE = "You should provide some `Activity` message";
    // Fields
    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    private void setName(String name) {
        validateStringLength(name,BOARD_NAME_MIN_LENGTH, BOARD_NAME_MIN_LENGTH, BOARD_NAME_LENGTH_ERR);
        // TODO - Consider checking for unique name in the CommandFactory (eventually)
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public void addActivity(String activity) {
        // TODO - Check if string is null
        if (activity.isEmpty() || activity.isBlank()) {
            throw new InvalidUserInputException(ACTIVITY_NULL_MESSAGE);
        }
        activityHistory.add(activity);
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        addActivity(ADD_TASK_SUCCESSFUL_MESSAGE + task.getTitle());
    }
}
