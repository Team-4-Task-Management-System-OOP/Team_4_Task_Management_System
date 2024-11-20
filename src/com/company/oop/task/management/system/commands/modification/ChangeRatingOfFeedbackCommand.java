package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangeRatingOfFeedbackCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;


    public ChangeRatingOfFeedbackCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int feedbackId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_INPUT_MESSAGE);
        int newRating = ParsingHelpers.tryParseInt(parameters.get(1), INVALID_RATING_VALUE);
        String teamName = parameters.get(2);

        if (newRating < 1 || newRating > 10) {
            throw new InvalidUserInputException(RATING_ERROR);

        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new ElementNotFoundException(String.format(NO_TEAMS_FOUND));
        }

        Feedback feedback = getTaskManagementSystemRepository().findTaskById(getTaskManagementSystemRepository().getFeedbacks(), feedbackId);

        int oldRating = feedback.getFeedbackRating();

        feedback.changeFeedbackRating(newRating);

        feedback.historyLogger(String.format(
                FEEDBACK_RATING_CHANGED, oldRating, newRating, feedback.getId()));

        return String.format(SUCCESSFULLY_CHANGED_THE_RATING_OF_FEEDBACK, feedbackId, newRating);
    }
}
