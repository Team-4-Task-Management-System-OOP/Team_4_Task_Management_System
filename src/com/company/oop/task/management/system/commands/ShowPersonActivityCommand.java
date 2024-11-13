package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;

public class ShowPersonActivityCommand implements Command {
    public ShowPersonActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
    }
}