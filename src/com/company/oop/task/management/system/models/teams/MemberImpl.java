package com.company.oop.task.management.system.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Assignable;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class MemberImpl implements Member {
    //Constants
    private static final int MEMBER_NAME_MIN_LENGTH = 5;
    private static final int MEMBER_NAME_MAX_LENGTH = 15;
    private static final String NAME_ERR = "%s is an invalid member name! " +
            "Name must be a between %d and %d characters!";

    //Fields
    private String name;
    private Team team;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    //Constructor
    public MemberImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    //Setters
    private void setName(String name) {
        validateStringLength(name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH,
                format(NAME_ERR, name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH));
        // TODO - Consider checking for unique name in the CommandFactory (eventually)
        this.name = name;
    }

    //ToDo is this a good functionality?
    @Override
    public void setTeam(Team team) {
        this.team = team;
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
    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public Team getTeam() {
        return team;
    }

    //Methods
    @Override
    public void addTask(Task task) {
        if (task == null) {
            throw new InvalidUserInputException("Cannot add an empty task.");
        }
        tasks.add(task);
        addActivityHistory("The following task has been assigned: " + task);
    }

    @Override
    public void removeTask(Task task) {
        if (task == null) {
            throw new InvalidUserInputException("Cannot remove an empty task.");
        }
        if (tasks.stream().anyMatch(m -> m.getTitle().equals(task.getTitle()))) {
            tasks.remove(task);
            addActivityHistory("The following task has been unassigned: " + task);
        } else {
            throw new InvalidUserInputException("Task cannot be removed! It has not been assigned " +
                    "or has already been completed!");
        }
    }

    @Override
    public void addActivityHistory(String history) {
        activityHistory.add(history);
    }

    @Override
    public String printHistory() {
        StringBuilder printHistory = new StringBuilder();
        for (String history : activityHistory) {
            printHistory.append(history).append(System.lineSeparator());
        }
        return printHistory.toString();
    }

    //Print
    @Override
    public String toString() {
        return  format("%nName: %s" +
                "%n---Tasks---%n%s" +
                "%n---History---%n%s"+
                "%nTeam: %s" +
                "%n", getName(), tasks.toString(), printHistory(), team.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberImpl member = (MemberImpl) o;
        return Objects.equals(name, member.name);
    }

}

