package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.FeedbackImpl;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.BOARD_DOES_NOT_EXIST_IN_TEAM;
import static com.company.oop.task.management.system.commands.utils.CommandsConstants.FEEDBACK_CREATED;
import static com.company.oop.task.management.system.models.tasks.FeedbackImpl.*;
import static java.lang.String.format;

public class CreateFeedbackInBoardCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

    public CreateFeedbackInBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

//    @Override
//    protected String executeCommand(List<String> parameters) {
//        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
//        String title = parameters.get(0);
//        String description = parameters.get(1);
//        int rating = ParsingHelpers.tryParseInt(parameters.get(2),
//                (format(RATING_ERR, RATING_MIN, RATING_MAX)));
//        String boardNameToAddFeedbackIn = parameters.get(3);
//        Board boardToAddFeedbackIn = getTaskManagementSystemRepository().findBoardByName(boardNameToAddFeedbackIn);
//        if (getTaskManagementSystemRepository()
//                .getLoggedInTeam().getBoards().contains(boardToAddFeedbackIn)) {
//            throw new InvalidUserInputException(format(BOARD_DOES_NOT_EXIST_IN_TEAM,
//                    boardNameToAddFeedbackIn,
//                    getTaskManagementSystemRepository().getLoggedInTeam().getName()));
//        }
//        Feedback feedback = getTaskManagementSystemRepository().createFeedback(title, description, rating);
//        boardToAddFeedbackIn.addTask(feedback);
//        feedback.historyLogger(format(FEEDBACK_CREATED, feedback.getId(), feedback.getTitle()));
//        return format(FEEDBACK_CREATED, feedback.getId(), feedback.getTitle());
//    }

    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        int rating = ParsingHelpers.tryParseInt(parameters.get(2), format(RATING_ERR, RATING_MIN, RATING_MAX));
        String boardNameToAddFeedbackIn = parameters.get(3);

        Team loggedInTeam = getTaskManagementSystemRepository().getLoggedInTeam();

        boolean boardExistsInTeam = loggedInTeam.getBoards()
                .stream()
                .anyMatch(board -> board.getName().equalsIgnoreCase(boardNameToAddFeedbackIn));

        if (!boardExistsInTeam) {
            throw new InvalidUserInputException(format(BOARD_DOES_NOT_EXIST_IN_TEAM,
                    boardNameToAddFeedbackIn,
                    loggedInTeam.getName()));
        }

        Board boardToAddFeedbackIn = getTaskManagementSystemRepository().findBoardByName(boardNameToAddFeedbackIn);

        Feedback feedback = getTaskManagementSystemRepository().createFeedback(title, description, rating);
        boardToAddFeedbackIn.addTask(feedback);
        feedback.historyLogger(format(FEEDBACK_CREATED, feedback.getId(), feedback.getTitle()));

        return format(FEEDBACK_CREATED, feedback.getId(), feedback.getTitle());
    }
//    private void validateAction() {
//        Team loggedMemberTeam = getTaskManagementSystemRepository().getLoggedInTeam().getTeam();
//        if (loggedMemberTeam == null || !loggedMemberTeam.getBoards().contains(board)) {
//            throw new IllegalArgumentException(String.format(TEAMS_NOT_MATCHING_BOARD, board.getName()));
//        }
//    }

        @Override
    protected boolean requiresLogin() {
        return true;
    }
}
