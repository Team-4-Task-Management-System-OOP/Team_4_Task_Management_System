package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public abstract class BaseCommand implements Command {

    private final static String MEMBER_NOT_LOGGED = "You are not logged in! Please login first!";

    private static TaskManagementSystemRepository taskManagementSystemRepository;

    public BaseCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        BaseCommand.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    protected static TaskManagementSystemRepository getTaskManagementSystemRepository() {
        return taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
//        if (requiresLogin() && !taskManagementSystemRepository.hasLoggedInMember()) {
//            throw new IllegalArgumentException(MEMBER_NOT_LOGGED);
//        }
        return executeCommand(parameters);
    }

//    protected abstract boolean requiresLogin();

    protected abstract String executeCommand(List<String> parameters);
}
