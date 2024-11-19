package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;
import static com.company.oop.task.management.system.commands.utils.CommandsConstants.JOIN_DELIMITER;
import static java.lang.String.format;

public class ShowAllTeamMembersCommand extends BaseCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowAllTeamMembersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
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
        Team teamToShowMembers = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (teamToShowMembers.getMembers().isEmpty()) {
            return NO_MEMBERS_FOUND_IN_TEAM;
        }
        StringBuilder allMembersInTeamPrint = new StringBuilder();
        allMembersInTeamPrint.append(format(ALL_MEMBERS_IN_TEAM_MESSAGE, teamToShowMembers.getName()));
        for (int i = 0; i < teamToShowMembers.getMembers().size(); i++) {
            allMembersInTeamPrint.append(format(MEMBER_HEADLINE, i + 1)).append(teamToShowMembers.getMembers().get(i).toString());
            if (i < teamToShowMembers.getMembers().size() - 1) {
                allMembersInTeamPrint.append(System.lineSeparator());
                allMembersInTeamPrint.append(JOIN_DELIMITER).append(System.lineSeparator());
            }
        }

//        Optional<Team> optionalTeam = getTaskManagementSystemRepository()
//                .getTeams()
//                .stream()
//                .filter(team -> team.getName().equalsIgnoreCase(teamName))
//                .findFirst();
//
//        if (optionalTeam.isEmpty()) {
//            return String.format(TEAM_NOT_FOUND, teamName);
//        }
//        Team team = optionalTeam.get();
//        List<Member> members = team.getMembers();
//
//        if (members.isEmpty()) {
//            return String.format(NO_MEMBERS_FOUND_IN_TEAM, teamName);
//        }
//        StringBuilder membersInfo = new StringBuilder("Team Members:\n");
//        members.forEach(member -> membersInfo.append(String.format(
//                "Name: %s, Tasks: %d, Activity History: %d entries\n",
//                member.getName(),
//                member.getTasks().size(),
//                member.getActivityHistory().size())));
        return allMembersInTeamPrint.toString();
    }
}
