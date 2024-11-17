package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;
import static java.lang.String.format;

public class ShowAllTeamBoardsCommand extends BaseCommand implements Command {

    public static final String SPECIFY_A_TEAM_NAME_TO_SHOW_ITS_BOARDS = "Please specify a team name to show its boards.";
    public static final String TEAM_NOT_FOUND = "Team '%s' not found.";
    public static final String NO_BOARDS_FOUND_IN_TEAM = "No boards found in team '%s'.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowAllTeamBoardsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        List<Board> allTeamBoards = getTaskManagementSystemRepository().findTeamByName(teamName).getBoards();
        if (allTeamBoards.isEmpty()) {
            return format(NO_BOARDS_FOUND, teamName, teamName);
        }
        else {
            StringBuilder teamBoardsToPrint = new StringBuilder();
            teamBoardsToPrint.append(ALL_TEAM_BOARDS_MESSAGE);
            for (int i = 0; i < allTeamBoards.size(); i++) {
                teamBoardsToPrint.append(format(BOARD_HEADLINE, i + 1)).append(allTeamBoards.get(i).toString());
                if (i < allTeamBoards.size() - 1) {
                    teamBoardsToPrint.append(System.lineSeparator());
                    teamBoardsToPrint.append(JOIN_DELIMITER).append(System.lineSeparator());
                }
            }
            return teamBoardsToPrint.toString();
        }

//        for (Board board : allTeamBoards) {
//            if (board.getName().equalsIgnoreCase(teamName)) {
//                team = board;
//                break;
//            }
//            else return format(TEAM_NOT_FOUND, teamName);
//        }
//
//        List<Board> boards = team.getBoards();
//
//        if (boards.isEmpty()) {
//            return format(NO_BOARDS_FOUND_IN_TEAM, teamName);
//        }
//
//        StringBuilder boardsInfo = new StringBuilder("Team Boards:\n");
//        for (Board board : boards) {
//            boardsInfo.append(format(
//                    "Board Name: %s, Tasks: %d, Activity History: %d entries\n",
//                    board.getName(),
//                    board.getTasks().size(),
//                    board.getHistory().size()));
//        }
//        return boardsInfo.toString();
    }
}
