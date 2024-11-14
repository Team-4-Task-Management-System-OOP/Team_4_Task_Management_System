package com.company.oop.task.management.system.commands.security;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class LogoutCommand extends BaseCommand {
    public final static String USER_LOGGED_OUT = "You logged out!";

    public LogoutCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        getTaskManagementSystemRepository().logout();
        return USER_LOGGED_OUT;
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

}
