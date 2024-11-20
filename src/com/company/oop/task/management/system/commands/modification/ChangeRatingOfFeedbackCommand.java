package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
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

        String feedbackId = parameters.get(0);
        String newRatingStr = parameters.get(1);
        String teamName = parameters.get(2);

        int newRating;
        try {
            newRating = Integer.parseInt(newRatingStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_RATING_VALUE);
        }

        if (newRating < 1 || newRating > 10) {
            throw new IllegalArgumentException(RATING_ERROR);

        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(String.format(NO_TEAMS_FOUND));
        }

        Feedback feedback = getTaskManagementSystemRepository().findTaskById(getTaskManagementSystemRepository().getFeedbacks(), feedbackId);

        int oldRating = feedback.getFeedbackRating();
        feedback.changeFeedbackRating(newRating);
        feedback.historyLogger(String.format(
                FEEDBACK_RATING_CHANGED, oldRating, newRating, feedback.getId()
        ));

        return String.format(SUCCESSFULLY_CHANGED_THE_RATING_OF_FEEDBACK, feedbackId, newRating);
    }
}
