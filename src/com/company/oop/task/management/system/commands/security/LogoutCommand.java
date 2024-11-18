package com.company.oop.task.management.system.commands.security;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.USER_LOGGED_OUT;
import static java.lang.String.format;

public class LogoutCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;


    public LogoutCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String loggedOutMessage = format(USER_LOGGED_OUT, getTaskManagementSystemRepository().getLoggedInMember().getName());
        getTaskManagementSystemRepository().getLoggedInMember().addActivityHistory(loggedOutMessage);
        getTaskManagementSystemRepository().logoutMember();
        getTaskManagementSystemRepository().logoutTeam();
        return format(loggedOutMessage);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

}
