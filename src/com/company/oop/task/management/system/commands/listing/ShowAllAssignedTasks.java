package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Assignable;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_REGISTERED_ASSIGNED_TASKS;
import static com.company.oop.task.management.system.utils.ListingHelpers.listImportantInfoForAllTasks;

public class ShowAllAssignedTasks extends BaseCommand {
    private final List<Assignable> assignedTasks;

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ShowAllAssignedTasks(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        assignedTasks = taskManagementSystemRepository.getAssignedTasks();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        if (assignedTasks.isEmpty()) {
            throw new ElementNotFoundException(NO_REGISTERED_ASSIGNED_TASKS);
        }
        return listImportantInfoForAllTasks(assignedTasks);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
