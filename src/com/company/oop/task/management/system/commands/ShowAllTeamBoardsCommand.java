package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.List;

public class ShowAllTeamBoardsCommand extends BaseCommand implements Command {

    public static final String SPECIFY_A_TEAM_NAME_TO_SHOW_ITS_BOARDS = "Please specify a team name to show its boards.";
    public static final String TEAM_NOT_FOUND = "Team '%s' not found.";
    public static final String NO_BOARDS_FOUND_IN_TEAM = "No boards found in team '%s'.";

    public ShowAllTeamBoardsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (parameters.isEmpty()) {
            return SPECIFY_A_TEAM_NAME_TO_SHOW_ITS_BOARDS;
        }
        String teamName = parameters.getFirst();

        Team team = null;
        for (Team t : getTaskManagementSystemRepository().getTeams()) {
            if (t.getName().equalsIgnoreCase(teamName)) {
                team = t;
                break;
            }
        }

        if (team == null) {
            return String.format(TEAM_NOT_FOUND, teamName);
        }

        List<Board> boards = team.getBoards();

        if (boards.isEmpty()) {
            return String.format(NO_BOARDS_FOUND_IN_TEAM, teamName);
        }

        StringBuilder boardsInfo = new StringBuilder("Team Boards:\n");
        for (Board board : boards) {
            boardsInfo.append(String.format(
                    "Board Name: %s, Tasks: %d, Activity History: %d entries\n",
                    board.getName(),
                    board.getTasks().size(),
                    board.getHistory().size()));
        }
        return boardsInfo.toString();
    }
}
