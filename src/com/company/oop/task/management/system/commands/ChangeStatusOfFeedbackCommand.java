package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementRepository;

public class ChangeStatusOfFeedbackCommand implements Command {
    public ChangeStatusOfFeedbackCommand(TaskManagementRepository taskManagementRepository) {
    }
}