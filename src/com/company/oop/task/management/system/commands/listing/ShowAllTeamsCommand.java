package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;
import static java.lang.String.format;

public class ShowAllTeamsCommand extends BaseCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ShowAllTeamsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        List<Team> allTeams = getTaskManagementSystemRepository().getTeams();
        if (allTeams.isEmpty()) {
            return NO_TEAMS_FOUND;
        }
        StringBuilder allTeamsPrint = new StringBuilder();
        allTeamsPrint.append(ALL_TEAMS_MESSAGE);
        for (int i = 0; i < allTeams.size(); i++) {
            allTeamsPrint.append(format(TEAM_HEADLINE, i + 1)).append(allTeams.get(i).toString());
            if (i < allTeams.size() - 1) {
                allTeamsPrint.append(System.lineSeparator());
                allTeamsPrint.append(JOIN_DELIMITER).append(System.lineSeparator());
            }
        }
        return allTeamsPrint.toString();
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
}
