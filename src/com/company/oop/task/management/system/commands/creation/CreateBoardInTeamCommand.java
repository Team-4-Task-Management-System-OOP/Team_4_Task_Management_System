package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.BOARD_CREATED_AND_ADDED;
import static java.lang.String.format;

public class CreateBoardInTeamCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public CreateBoardInTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        Team teamToAddBoardTo = getTaskManagementSystemRepository().findTeamByName(teamName);
        Board boardNew = getTaskManagementSystemRepository().createBoard(boardName, teamToAddBoardTo);
        teamToAddBoardTo.addActivityHistory(format(BOARD_CREATED_AND_ADDED, boardName, teamName));
        boardNew.addActivityHistory(format(BOARD_CREATED_AND_ADDED, boardName, teamName));
        return format(BOARD_CREATED_AND_ADDED, boardName, teamName);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
