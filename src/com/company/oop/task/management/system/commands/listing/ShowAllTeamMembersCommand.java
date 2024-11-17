package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.List;
import java.util.Optional;

public class ShowAllTeamMembersCommand extends BaseCommand implements Command {

    public static final String PLEASE_SPECIFY_A_TEAM_NAME = "Please specify a team name.";
    public static final String TEAM_NOT_FOUND = "Team '%s' not found.";
    public static final String NO_MEMBERS_FOUND_IN_TEAM = "No members found in team '%s'.";

    public ShowAllTeamMembersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
//ToDo
    @Override
    protected String executeCommand(List<String> parameters) {
        if (parameters.isEmpty()) {
            return PLEASE_SPECIFY_A_TEAM_NAME;
        }
        String teamName = parameters.get(0);
        Optional<Team> optionalTeam = getTaskManagementSystemRepository()
                .getTeams()
                .stream()
                .filter(team -> team.getName().equalsIgnoreCase(teamName))
                .findFirst();

        if (optionalTeam.isEmpty()) {
            return String.format(TEAM_NOT_FOUND, teamName);
        }
        Team team = optionalTeam.get();
        List<Member> members = team.getMembers();

        if (members.isEmpty()) {
            return String.format(NO_MEMBERS_FOUND_IN_TEAM, teamName);
        }
        StringBuilder membersInfo = new StringBuilder("Team Members:\n");
        members.forEach(member -> membersInfo.append(String.format(
                "Name: %s, Tasks: %d, Activity History: %d entries\n",
                member.getName(),
                member.getTasks().size(),
                member.getActivityHistory().size())));
        return membersInfo.toString();
    }
}
