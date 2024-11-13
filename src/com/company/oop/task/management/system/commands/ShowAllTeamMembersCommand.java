package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;

public class ShowAllTeamMembersCommand implements Command {
    public ShowAllTeamMembersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
    }
}
