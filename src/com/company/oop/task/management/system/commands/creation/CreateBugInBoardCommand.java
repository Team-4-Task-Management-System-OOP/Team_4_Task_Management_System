package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.enums.BugSeverity;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;
import static java.lang.String.format;

public class CreateBugInBoardCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    public CreateBugInBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String description = parameters.get(1);
        List<String> stepsToReproduce = ParsingHelpers.splitList(parameters.get(2), INVALID_REPRODUCIBLE_STEPS);
        PriorityType bugPrioritytype = ParsingHelpers.tryParseEnum(parameters.get(3), PriorityType.class);
        BugSeverity bugSeverity = ParsingHelpers.tryParseEnum(parameters.get(4), BugSeverity.class);
        String boardNameToAddBugIn = parameters.get(5);
        Team loggedInTeam = getTaskManagementSystemRepository().getLoggedInTeam();
        boolean boardExistsInTeam = loggedInTeam.getBoards()
                .stream()
                .anyMatch(board -> board.getName().equalsIgnoreCase(boardNameToAddBugIn));

        if (!boardExistsInTeam) {
            throw new InvalidUserInputException(format(BOARD_DOES_NOT_EXIST_IN_TEAM,
                    boardNameToAddBugIn,
                    loggedInTeam.getName()));
        }

        Board boardToAddBugIn = getTaskManagementSystemRepository().findBoardByName(boardNameToAddBugIn);
        Bug bug = getTaskManagementSystemRepository()
                .createBug(title, description, stepsToReproduce, bugPrioritytype, bugSeverity);
        boardToAddBugIn.addTask(bug);
        bug.historyLogger(format(BUG_CREATED, bug.getId(), bug.getTitle()));
        return format(BUG_CREATED, bug.getId(), bug.getTitle());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

}
