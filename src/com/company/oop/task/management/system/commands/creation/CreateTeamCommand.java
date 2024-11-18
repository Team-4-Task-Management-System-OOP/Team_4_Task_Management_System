package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.TEAM_CREATION_SUCCESSFUL_MESSAGE;
import static java.lang.String.format;

public class CreateTeamCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public CreateTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        Team currentTeam = getTaskManagementSystemRepository().createTeam(teamName);
        currentTeam.addActivityHistory(format(TEAM_CREATION_SUCCESSFUL_MESSAGE, teamName));
        return format(TEAM_CREATION_SUCCESSFUL_MESSAGE, currentTeam.getName());
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
}

