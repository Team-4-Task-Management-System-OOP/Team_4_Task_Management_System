package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.MemberImpl;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

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
        String teamName = parameters.get(0);
        String memberName = parameters.get(1);

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(NO_TEAMS_FOUND);
        }

        if (team.getMembers()
                .stream()
                .anyMatch(member -> member.getName().equalsIgnoreCase(memberName))) {
            throw new IllegalArgumentException(MEMBER_ALREADY_IN_TEAM);
        }

        Member member = getTaskManagementSystemRepository().findMemberByName(memberName);
        team.addMember(member);

        member.addActivityHistory(String.format(MEMBER_CREATION_SUCCESSFUL_MESSAGE, memberName));
        member.addActivityHistory(String.format(MEMBER_CREATION_SUCCESSFUL_MESSAGE, memberName));
        return String.format(MEMBER_ADDED, memberName, teamName);
    }
}
