package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangePriorityOfBugCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String INVALID_PRIORITY_VALUE = "Invalid priority value: '%s'. Valid values are: LOW, MEDIUM, HIGH.";

    public ChangePriorityOfBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String newPriority = parameters.get(0);
        String bugName = parameters.get(1);
        String boardName = parameters.get(2);
        String teamName = parameters.get(3);

        PriorityType priority;
        try {
            priority = PriorityType.valueOf(newPriority.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(INVALID_PRIORITY_VALUE, newPriority));
        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(String.format(NO_TEAMS_FOUND));
        }

        Board board = team.getBoards()
                .stream()
                .filter(b -> b.getName().equalsIgnoreCase(boardName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(NO_BOARDS_FOUND, teamName, teamName)
                ));

        Bug bug = board.getTasks()
                .stream()
                .filter(task -> task instanceof Bug)
                .map(task -> (Bug) task)
                .filter(b -> b.getTitle().equalsIgnoreCase(bugName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(NO_REGISTERED_BUGS)
                ));

        bug.changePriority(priority);

        bug.historyLogger(String.format(ENUM_CHANGED, bug.getId(), priority, newPriority));
        return String.format(BUG_PRIORITY_CHANGED);
    }
}
