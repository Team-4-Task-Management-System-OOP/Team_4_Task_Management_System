package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.MEMBER_NOT_LOGGED;

public abstract class BaseCommand implements Command {

    private static TaskManagementSystemRepository taskManagementSystemRepository;

    public BaseCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        BaseCommand.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    protected static TaskManagementSystemRepository getTaskManagementSystemRepository() {
        return taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (requiresLogin() && !taskManagementSystemRepository.hasLoggedInMember()) {
            throw new InvalidUserInputException(MEMBER_NOT_LOGGED);
        }
        return executeCommand(parameters);
    }

    protected abstract boolean requiresLogin();

    protected abstract String executeCommand(List<String> parameters);

}
