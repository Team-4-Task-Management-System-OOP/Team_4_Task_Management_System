package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementRepository;

public class FilterTasksByStatusCommand implements Command {
    public FilterTasksByStatusCommand(TaskManagementRepository taskManagementRepository) {
    }
}
