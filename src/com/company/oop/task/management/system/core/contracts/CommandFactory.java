package com.company.oop.task.management.system.core.contracts;

import com.company.oop.task.management.system.commands.contracts.Command;

public interface CommandFactory {

    Command createCommandFromCommandName(String commandTypeAsString,
                                         TaskManagementSystemRepository taskManagementSystemRepository);
}
