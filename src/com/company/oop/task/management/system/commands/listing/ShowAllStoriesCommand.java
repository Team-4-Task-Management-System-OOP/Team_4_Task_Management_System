package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_REGISTERED_STORIES;
import static com.company.oop.task.management.system.utils.ListingHelpers.listImportantInfoForAllTasks;

public class ShowAllStoriesCommand extends BaseCommand {
    private final List<Story> stories;
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;


    public ShowAllStoriesCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        stories = taskManagementSystemRepository.getStories();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        if (stories.isEmpty()) {
            throw new ElementNotFoundException(NO_REGISTERED_STORIES);
        }
        return listImportantInfoForAllTasks(stories);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
