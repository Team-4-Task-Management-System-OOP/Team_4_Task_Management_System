package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.CommandsConstants;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.List;

import static com.company.oop.task.management.system.utils.ListingHelpers.elementsToString;

public class ListAllTasksCommand extends BaseCommand {

    private final List<Task> tasks;

    public ListAllTasksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        tasks = taskManagementSystemRepository.getTasks();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if(tasks.isEmpty()){
            throw new ElementNotFoundException(CommandsConstants.NO_REGISTERED_TASKS);
        }
        return elementsToString(tasks);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
