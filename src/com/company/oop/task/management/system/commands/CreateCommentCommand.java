package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class CreateCommentCommand extends BaseCommand{


    public CreateCommentCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return "";
    }
}
