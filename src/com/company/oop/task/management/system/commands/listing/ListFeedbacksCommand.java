package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.CommandsConstants;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;

import java.util.List;

import static com.company.oop.task.management.system.utils.ListingHelpers.elementsToString;

public class ListFeedbacksCommand extends BaseCommand {

    private final List<Feedback> feedbacks;

    public ListFeedbacksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        feedbacks = taskManagementSystemRepository.getFeedbacks();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (feedbacks.isEmpty()) {
            throw new ElementNotFoundException(CommandsConstants.NO_REGISTERED_FEEDBACKS);
        }
        return elementsToString(feedbacks);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
