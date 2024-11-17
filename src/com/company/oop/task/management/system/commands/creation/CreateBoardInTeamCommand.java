package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

public class CreateBoardInTeamCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private String boardName;
    private String teamName;
    private Board board;
    private Team team;

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


        return "";
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
