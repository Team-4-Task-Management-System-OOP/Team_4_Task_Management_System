package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_REGISTERED_FEEDBACKS;
import static com.company.oop.task.management.system.utils.ListingHelpers.listImportantInfoForAllTasks;

public class ShowAllFeedbacksCommand extends BaseCommand {
    private final List<Feedback> feedbacks;
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ShowAllFeedbacksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        feedbacks = taskManagementSystemRepository.getFeedbacks();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        if (feedbacks.isEmpty()) {
            throw new ElementNotFoundException(NO_REGISTERED_FEEDBACKS);
        }
        return listImportantInfoForAllTasks(feedbacks);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
