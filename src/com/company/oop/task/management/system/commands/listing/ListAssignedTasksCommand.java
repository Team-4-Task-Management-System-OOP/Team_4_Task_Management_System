package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.CommandsConstants;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Assignable;

import java.util.List;

import static com.company.oop.task.management.system.utils.ListingHelpers.elementsToString;

public class ListAssignedTasksCommand extends BaseCommand {
    private final List<Assignable> assignedTasks;

    public ListAssignedTasksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        assignedTasks = taskManagementSystemRepository.getAssignedTasks();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (assignedTasks.isEmpty()) {
            throw new ElementNotFoundException(CommandsConstants.NO_REGISTERED_ASSIGNED_TASKS);
        }
        return elementsToString(assignedTasks);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
