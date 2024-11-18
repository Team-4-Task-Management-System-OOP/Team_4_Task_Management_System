package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_REGISTERED_TASKS;
import static com.company.oop.task.management.system.utils.ListingHelpers.listAllTasksSortedByTitle;

public class SortAllTasksByTitleCommand extends BaseCommand {

    private final List<Task> tasks;

    public SortAllTasksByTitleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        tasks = taskManagementSystemRepository.getTasks();
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (tasks.isEmpty()) {
            throw new ElementNotFoundException(NO_REGISTERED_TASKS);
        }
        return listAllTasksSortedByTitle(tasks);
    }
}