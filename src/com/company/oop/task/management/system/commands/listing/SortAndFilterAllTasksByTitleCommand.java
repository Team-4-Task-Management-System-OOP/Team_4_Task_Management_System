package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_REGISTERED_TASKS;
import static com.company.oop.task.management.system.utils.ListingHelpers.listAllTasksSortedAndFilteredByTitle;

public class SortAndFilterAllTasksByTitleCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final List<Task> tasks;

    public SortAndFilterAllTasksByTitleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        tasks = taskManagementSystemRepository.getTasks();
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String targetTitle = parameters.get(0);
        if (tasks.isEmpty() || tasks.stream().noneMatch(t -> t.getTitle().toLowerCase().contains(targetTitle.toLowerCase()))) {
            throw new ElementNotFoundException(NO_REGISTERED_TASKS);
        }
        return listAllTasksSortedAndFilteredByTitle(tasks,targetTitle);
    }

}
