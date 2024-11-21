package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;
import static com.company.oop.task.management.system.models.teams.MemberImpl.DEFAULT_TEAM;
import static java.lang.String.format;

public class AddMemberToTeamCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public AddMemberToTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String memberName = parameters.get(0);
        String teamName = parameters.get(1);

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new ElementNotFoundException(NO_TEAMS_FOUND);
        }

        if (team.getMembers()
                .stream()
                .anyMatch(member -> member.getName().equalsIgnoreCase(memberName))) {
            throw new InvalidUserInputException(format(MEMBER_ALREADY_IN_TEAM, memberName));
        }

        Member member = getTaskManagementSystemRepository().findMemberByName(memberName);

        if (member.getTeam() != DEFAULT_TEAM){
            member.getTeam().addActivityHistory(format(MEMBER_REMOVED_FROM_PREVIOUS_TEAM,
                    member.getName(), member.getTeam().getName()));
        }

        team.addMember(member);
        member.setTeam(team);

        return format(MEMBER_ADDED, memberName, teamName);
    }
}
