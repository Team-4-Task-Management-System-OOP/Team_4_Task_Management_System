package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.utils.CommandsConstants;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Story;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_REGISTERED_STORIES;
import static com.company.oop.task.management.system.utils.ListingHelpers.elementsToString;

public class ListStoriesCommand extends BaseCommand {
//ToDo ne e taka
    private final List<Story> stories;

    public ListStoriesCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        stories = taskManagementSystemRepository.getStories();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (stories.isEmpty()) {
            throw new ElementNotFoundException(NO_REGISTERED_STORIES);
        }
        return elementsToString(stories);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
