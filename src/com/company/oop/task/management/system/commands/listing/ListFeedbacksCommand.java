package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.utils.CommandsConstants;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_REGISTERED_FEEDBACKS;
import static com.company.oop.task.management.system.utils.ListingHelpers.elementsToString;

public class ListFeedbacksCommand extends BaseCommand {
    //ToDo ne e taka
    private final List<Feedback> feedbacks;

    public ListFeedbacksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        feedbacks = taskManagementSystemRepository.getFeedbacks();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (feedbacks.isEmpty()) {
            throw new ElementNotFoundException(NO_REGISTERED_FEEDBACKS);
        }
        return elementsToString(feedbacks);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
