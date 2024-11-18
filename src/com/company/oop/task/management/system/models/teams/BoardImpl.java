package com.company.oop.task.management.system.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.teams.contracts.Board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.utils.ParsingHelpers.formatTime;
import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class BoardImpl implements Board {

    private static final int BOARD_NAME_MIN_LENGTH = 5;
    private static final int BOARD_NAME_MAX_LENGTH = 10;
    private static final String BOARD_NAME_LENGTH_ERR = format(
            "Board's name must be between %d and %d symbols long!",
            BOARD_NAME_MIN_LENGTH,
            BOARD_NAME_MAX_LENGTH);
    private static final String CANNOT_ADD_AN_EMPTY_TASK_BOARD = "Cannot add an empty task to the board!";
    private static final String TASK_ADDED_TO_BOARD = "Task with name ''%s'' has been added to board ''%s''";
    private static final String ALREADY_ADDED = "Task with title %s is already added to board ''%s''";
    private static final String CANNOT_REMOVE_AN_EMPTY_TASK = "Cannot remove an empty task.";
    private static final String TASK_REMOVED_FROM_BOARD = "The following task with title ''%s'' " +
            "has been removed from board ''%s''";
    private static final String TASK_REMOVE_ERR = "Task cannot be removed! It has not been created yet";
    private static final String NO_HISTORY = "---NO BOARD HISTORY TO DISPLAY---\nDo some activities first!\n";
    private static final String NO_TASKS = "---NO TASKS IN BOARD'S LIST TO DISPLAY---\nAdd a task first!\n";

    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    private void setName(String name) {
        validateStringLength(name,BOARD_NAME_MIN_LENGTH, BOARD_NAME_MAX_LENGTH, BOARD_NAME_LENGTH_ERR);
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
    public void addActivityHistory(String history) {
        activityHistory.add(format("[%s] - %s", formatTime(LocalDateTime.now()), history));
    }

    @Override
    public void addTask(Task task) {
        if (task == null) {
            throw new InvalidUserInputException(CANNOT_ADD_AN_EMPTY_TASK_BOARD);
        }
        if (tasks.stream().noneMatch(m -> m.getTitle().equalsIgnoreCase(task.getTitle()))) {
            tasks.add(task);
            addActivityHistory(format(TASK_ADDED_TO_BOARD, task.getTitle(), getName()));
        }
        else {
            throw new InvalidUserInputException(format(ALREADY_ADDED, task.getTitle(), getName()));
        }
    }

    @Override
    public void removeTask(Task task) {
        if (task == null) {
            throw new InvalidUserInputException(CANNOT_REMOVE_AN_EMPTY_TASK);
        }
        if (tasks.stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(task.getTitle()))) {
            tasks.remove(task);
            addActivityHistory(format(TASK_REMOVED_FROM_BOARD, task.getTitle(), getName()));
        } else {
            throw new InvalidUserInputException(TASK_REMOVE_ERR);
        }
    }

    @Override
    public String printHistory() {
        if (getHistory().isEmpty() || getHistory() == null) {
            return NO_HISTORY;
        }
        else {
            StringBuilder printHistory = new StringBuilder();
            for (String history : activityHistory) {
                printHistory.append(history).append(System.lineSeparator());
            }
            return printHistory.toString();
        }
    }

    @Override
    public String printTasks() {
        if (getTasks().isEmpty() || getTasks() == null) {
            return NO_TASKS;
        }
        StringBuilder printTasks = new StringBuilder();
        for (Task task : tasks) {
            printTasks.append(task.getTitle()).append(System.lineSeparator());
        }
        return printTasks.toString();
    }

    @Override
    public String toString() {
        return format("%nBoard Name: %s" +
                "%n---Board Tasks---%n%s" +
                "%n---Board History---%n%s", getName(), printTasks(), printHistory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardImpl board = (BoardImpl) o;
        return name.equalsIgnoreCase(board.name);
    }
}
