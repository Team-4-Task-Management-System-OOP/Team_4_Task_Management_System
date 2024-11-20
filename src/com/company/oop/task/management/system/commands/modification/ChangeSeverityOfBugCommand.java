package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.enums.BugSeverity;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangeSeverityOfBugCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public ChangeSeverityOfBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
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
        String newSeverity = parameters.get(1);
        String teamName = parameters.get(2);

        BugSeverity severity;
        try {
            severity = BugSeverity.valueOf(newSeverity.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(INVALID_SEVERITY_VALUE, newSeverity));
        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(String.format(NO_TEAMS_FOUND));
        }

        Bug bug = getTaskManagementSystemRepository().findTaskById(getTaskManagementSystemRepository().getBugs(), bugId);

        BugSeverity oldSeverity = bug.getBugSeverity();
        bug.changeSeverity(severity);

        bug.historyLogger(String.format(BUG_SEVERITY_CHANGED_FROM, oldSeverity, severity, bug.getId()));
        return String.format(SUCCESSFULLY_CHANGED_THE_SEVERITY_OF_BUG, bugId, severity);
    }
}
