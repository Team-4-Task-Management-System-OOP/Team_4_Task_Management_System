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
    private static final int BOARD_NAME_MIN_LENGTH = 5;
    private static final int BOARD_NAME_MAX_LENGTH = 10;
    private static final String BOARD_NAME_LENGTH_ERR = format(
            "Board's name must be between %d and %d symbols long!",
            BOARD_NAME_MIN_LENGTH,
            BOARD_NAME_MAX_LENGTH);
    private static final String ADD_TASK_SUCCESSFUL_MESSAGE = "The following task has been added: ";
    private static final String ACTIVITY_NULL_MESSAGE = "Activity message cannot be empty! " +
            "You should provide some `Activity` message";

    // Fields
    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    //Constructor
    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    //Setters
    private void setName(String name) {
        validateStringLength(name,BOARD_NAME_MIN_LENGTH, BOARD_NAME_MIN_LENGTH, BOARD_NAME_LENGTH_ERR);
        // TODO - Consider checking for unique name in the CommandFactory (eventually)
        this.name = name;
    }

    //Getters
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

    //Methods
    @Override
    public void addActivityHistory(String activity) {
        if (activity.isEmpty() || activity.isBlank()) {
            throw new InvalidUserInputException(ACTIVITY_NULL_MESSAGE);
        }
        //Todo CONSIDER ADDING A TIME FOR THE ACTIVITY WITH PARSINGHELPER
        // FORMATTIME FOR THE CURRENT TIME OF THE EVENT(NOW)
        activityHistory.add(activity);
    }

    @Override
    public void addTask(Task task) {
        if (task == null) {
            throw new InvalidUserInputException("Cannot add an empty task!");
        }
        tasks.add(task);
        addActivityHistory(ADD_TASK_SUCCESSFUL_MESSAGE + task.getTitle());
    }

    //Print
    @Override
    public String toString() {
        return format("%nName: %s" +
                "%n---Tasks---%n%s" +
                "%n---History---%n%s" +
                "%n", getName(), tasks.toString(), activityHistory.toString());
    }
}
