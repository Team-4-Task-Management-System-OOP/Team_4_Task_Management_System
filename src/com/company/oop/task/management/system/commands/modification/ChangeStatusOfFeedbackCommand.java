package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.models.tasks.enums.FeedbackStatus;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;
import java.util.List;
import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangeStatusOfFeedbackCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public ChangeStatusOfFeedbackCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
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
        String newStatus = parameters.get(1);
        String teamName = parameters.get(2);

        FeedbackStatus status;
        try {
            status = FeedbackStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(INVALID_FEEDBACK_STATUS_VALUE, newStatus));
        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(NO_TEAMS_FOUND);
        }

        Feedback feedback = getTaskManagementSystemRepository().findTaskById(getTaskManagementSystemRepository().getFeedbacks(), feedbackId);

        FeedbackStatus oldStatus = feedback.getFeedbackStatus();
        feedback.changeFeedbackStatus(status);

        feedback.historyLogger(String.format(
                FEEDBACK_STATUS_CHANGED, oldStatus, status, feedback.getId()));

        return String.format(SUCCESSFULLY_CHANGED_THE_STATUS_OF_FEEDBACK, feedback.getId(), status);
    }
}