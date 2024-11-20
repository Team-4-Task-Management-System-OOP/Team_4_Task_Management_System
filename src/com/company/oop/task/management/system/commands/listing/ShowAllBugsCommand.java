package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.utils.ValidationHelpers;


import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_REGISTERED_BUGS;
import static com.company.oop.task.management.system.utils.ListingHelpers.listImportantInfoForAllTasks;

public class ShowAllBugsCommand extends BaseCommand {
    private final List<Bug> bugs;
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ShowAllBugsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        bugs = taskManagementSystemRepository.getBugs();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        if (bugs.isEmpty()) {
            throw new ElementNotFoundException(NO_REGISTERED_BUGS);
        }
        return listImportantInfoForAllTasks(bugs);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
