package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.List;

public class ShowAllTeamCommand extends BaseCommand implements Command {

    public static final String NO_TEAM_FOUND_IN_THE_SYSTEM = "No teams found in the system.";

    public ShowAllTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        List<Team> teams = getTaskManagementSystemRepository().getTeams();

        if (teams.isEmpty()) {
            return NO_TEAM_FOUND_IN_THE_SYSTEM;
        }
        StringBuilder teamsInfo = new StringBuilder("Teams:\n");
        for (Team team : teams) {
            teamsInfo.append("Team Name: ")
                    .append(team.getName())
                    .append(", Members: ")
                    .append(team.getMembers().size())
                    .append(", Boards: ")
                    .append(team.getBoards().size())
                    .append(" boards\n");
        }
        return teamsInfo.toString();
    }
}
