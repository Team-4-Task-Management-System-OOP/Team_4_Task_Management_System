package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.enums.BugStatus;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangeStatusOfBugCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

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

        int bugId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_INPUT_MESSAGE);
        String newStatus = parameters.get(1);
        String teamName = parameters.get(2);

        BugStatus status;
        try {
            status = BugStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUserInputException(String.format(INVALID_BUG_STATUS_VALUE, newStatus));
        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new ElementNotFoundException(String.format(NO_TEAM_FOUND, teamName));
        }

        Bug bug = getTaskManagementSystemRepository().findTaskById(getTaskManagementSystemRepository().getBugs(), bugId);
        bug.changeBugStatus(status);

        BugStatus oldStatus = bug.getBugStatus();

        bug.historyLogger(String.format(BUG_STATUS_CHANGED, oldStatus, status, bug.getId()));
        return String.format(SUCCESSFULLY_CHANGED_THE_STATUS_OF_BUG, bug.getId(), status);
    }
}
