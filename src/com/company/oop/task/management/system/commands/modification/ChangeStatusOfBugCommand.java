package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.enums.BugStatus;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangeStatusOfBugCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

    public ChangeStatusOfBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String newStatus = parameters.get(0);
        String bugName = parameters.get(1);
        String boardName = parameters.get(2);
        String teamName = parameters.get(3);

        BugStatus status;
        try {
            status = BugStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(INVALID_BUG_STATUS_VALUE, newStatus));
        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(NO_TEAMS_FOUND);
        }

        Board board = findBoardByName(team, boardName);
        Bug bug = findBugByName(board, bugName);

        BugStatus oldStatus = bug.getBugStatus();
        bug.changeBugStatus(status);
        bug.historyLogger(String.format(BUG_STATUS_CHANGED, oldStatus, status, bug.getId()));

        return String.format(SUCCESSFULLY_CHANGED_THE_STATUS_OF_BUG, bugName, status);
    }

    private Board findBoardByName(Team team, String boardName) {
        return team.getBoards()
                .stream()
                .filter(board -> board.getName().equalsIgnoreCase(boardName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(NO_BOARDS_FOUND, boardName, team.getName())));
    }

    private Bug findBugByName(Board board, String bugName) {
        return board.getTasks()
                .stream()
                .filter(task -> task instanceof Bug)
                .map(task -> (Bug) task)
                .filter(bug -> bug.getTitle().equalsIgnoreCase(bugName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_REGISTERED_BUGS));
    }
}
