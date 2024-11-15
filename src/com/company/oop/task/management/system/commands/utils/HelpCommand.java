package com.company.oop.task.management.system.commands.utils;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class HelpCommand extends BaseCommand {


    public HelpCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {


        return "";
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
}
