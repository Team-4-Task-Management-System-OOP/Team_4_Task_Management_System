package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementRepository;

public class CreateTeamCommand implements Command {
    public CreateTeamCommand(TaskManagementRepository taskManagementRepository) {
    }
}
