package com.company.oop.task.management.system.commands.listing.tasks;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_REGISTERED_TASKS_WITH_PARTICULAR_TITLE;
import static com.company.oop.task.management.system.utils.ListingHelpers.listAllTasksFilteredByTitle;
import static java.lang.String.format;

public class ShowAllTasksFilteredByTitleCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final List<Task> tasks;

    public ShowAllTasksFilteredByTitleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
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
        String targetTitleLower = targetTitle.toLowerCase();

        if (tasks.isEmpty() || tasks.stream().noneMatch(t -> t.getTitle().toLowerCase().contains(targetTitleLower))) {
            throw new ElementNotFoundException(format(NO_REGISTERED_TASKS_WITH_PARTICULAR_TITLE, targetTitle));
        }
        return listAllTasksFilteredByTitle(tasks, targetTitle);
    }
}
