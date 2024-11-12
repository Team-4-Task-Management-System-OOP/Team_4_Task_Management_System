package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.models.tasks.contracts.Board;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private static final double NAME_MIN_LENGTH = 5;
    private static final double NAME_MAX_LENGTH = 10;
    private static final String NAME_ERROR_MSG = String.format("Name must be %s and %s symbols,",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);

    private static final String NO_SUCH_TASK = "Task not found";

    private String name;
    private List<String> tasks;
    private List<String> activityHistory;

    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getTasks() {
        return tasks;
    }

    @Override
    public List<String> getActivityHistory() {
        return activityHistory;
    }

    public void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_ERROR_MSG);
        this.name = name;
    }

    public void AddActivity(String activity){
        if (activity != null){
            activityHistory.add(activity);
        } else {
            throw new IllegalArgumentException(NO_SUCH_TASK);
        }
    }

    @Override
    public void addTask(String task) {
        if (task != null){
            tasks.add(task);
            activityHistory.add(task);
        } else {
            throw new IllegalArgumentException(NO_SUCH_TASK);
        }
    }
}
