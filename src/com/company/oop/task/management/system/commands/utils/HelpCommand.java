package com.company.oop.task.management.system.commands.utils;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class HelpCommand extends BaseCommand {

    private static final String commands = "Welcome to the Task application of Ivan, Viktor and Dimitar!\n" +
            "Below is a list of available commands:\n\n" +
            "showallpeople --> Displays all member registered in the system";


    public HelpCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return commands;
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
}
