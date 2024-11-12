package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementRepository;

public class ChangeStatusOfBugCommand implements Command {
    public ChangeStatusOfBugCommand(TaskManagementRepository taskManagementRepository) {
    }
}
