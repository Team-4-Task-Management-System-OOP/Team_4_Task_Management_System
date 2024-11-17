package com.company.oop.task.management.system.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.utils.ParsingHelpers.formatTime;
import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class MemberImpl implements Member {

    //Constants
    private static final int MEMBER_NAME_MIN_LENGTH = 5;
    private static final int MEMBER_NAME_MAX_LENGTH = 15;
    private static final String NAME_ERR = "''%s'' is an invalid member name! " +
            "Name must be between %d and %d characters!";
    private static final String TASK_ADDED_TO_MEMBER_TASK_LIST = "The following task with title ''%s'' " +
            "has been added to the tasks of member ''%s''";
    private static final String TASK_REMOVED_FROM_MEMBER_TASK_LIST = "The following task with title ''%s'' " +
            "has been removed from the tasks of member %s:";
    private static final String TASK_REMOVE_ERR = "Task cannot be removed! It has not been created yet";
    private static final String CANNOT_REMOVE_AN_EMPTY_TASK = "Cannot remove an empty task.";
    private static final String ALREADY_ADDED = "Task with title ''%s'' is already added to the tasks of member ''%s''";
    private static final String CANNOT_ADD_AN_EMPTY_TASK_MEMBER = "Cannot add an empty task.";;
    private static final String NO_TASKS = "---NO TASKS IN MEMBER'S LIST TO DISPLAY---\nAdd a task first!\n";
    private static final String NO_HISTORY = "---NO MEMBER HISTORY TO DISPLAY---\nDo some activities first!\n";
    private static final Team DEFAULT_TEAM = new TeamImpl("Unassigned");

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
        this.team = DEFAULT_TEAM;
    }

    //Setters
    private void setName(String name) {
        validateStringLength(name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH,
                format(NAME_ERR, name, MEMBER_NAME_MIN_LENGTH, MEMBER_NAME_MAX_LENGTH));
        this.name = name;
    }

    @Override
    public void setTeam(Team teamName) {
        if (team.getName() == null || team.getName().equalsIgnoreCase(DEFAULT_TEAM.getName())) {
            this.team = teamName;
            addActivityHistory(format("Member %s assigned to team %s.", getName(), teamName.getName()));
        }
        if (this.team != null && this.team.getName().equalsIgnoreCase(teamName.getName())) {
            throw new InvalidUserInputException(format("Member %s is already part of this team!", getName()));
        }
        addActivityHistory(format("Member %s reassigned to team %s from team %s.",
                getName(), teamName.getName(), this.team.getName()));
        this.team = teamName;
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
            throw new InvalidUserInputException(CANNOT_ADD_AN_EMPTY_TASK_MEMBER);
        }
        if (tasks.stream().noneMatch(m -> m.getTitle().equalsIgnoreCase(task.getTitle()))) {
            tasks.add(task);
            addActivityHistory(format(TASK_ADDED_TO_MEMBER_TASK_LIST, task.getTitle(), getName()));
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
            addActivityHistory(format(TASK_REMOVED_FROM_MEMBER_TASK_LIST, task.getTitle(), getName()));
        } else {
            throw new InvalidUserInputException(TASK_REMOVE_ERR);
        }
    }

    @Override
    public void addActivityHistory(String history) {
        activityHistory.add(format("[%s] - %s", formatTime(LocalDateTime.now()), history));
    }

    @Override
    public String printHistory() {
        if (getActivityHistory().isEmpty() || getActivityHistory() == null) {
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
        else {
            StringBuilder printTasks = new StringBuilder();
            for (Task task : tasks) {
                printTasks.append(task.getTitle()).append(System.lineSeparator());
            }
            return printTasks.toString();
        }
    }

    //Print
    @Override
    public String toString() {
        return  format("%nMember Name: %s" +
                "%nMember Team: %s" +
                "%n---Member Tasks---%n%s" +
                "%n---Member History---%n%s", getName(), team.getName(), printTasks(), printHistory());
    }

    //Equals Override in order to make contains method work properly in repo
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberImpl member = (MemberImpl) o;
        return name.equalsIgnoreCase(member.name);
    }

}

