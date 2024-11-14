package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.CommandsConstants;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;


import java.util.List;

import static com.company.oop.task.management.system.utils.ListingHelpers.elementsToString;

public class ListBugsCommand extends BaseCommand {

    private final List<Bug> bugs;

    public ListBugsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        bugs = taskManagementSystemRepository.getBugs();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (bugs.isEmpty()) {
            throw new ElementNotFoundException(CommandsConstants.NO_REGISTERED_BUGS);
        }
        return elementsToString(bugs);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
