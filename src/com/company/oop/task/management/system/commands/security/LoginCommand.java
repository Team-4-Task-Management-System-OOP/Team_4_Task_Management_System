package com.company.oop.task.management.system.commands.security;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class LoginCommand extends BaseCommand {

    private final static String MEMBER_LOGGED_IN = "Member %s successfully logged in!";
    private final static String WRONG_MEMBER_OR_TEAM = "Your team's and member's name does not match!" +
            " Please, first provide a team, then its member to log in!";
    public final static String MEMBER_LOGGED_IN_ALREADY = "Member %s is already logged in! " +
            "Please log out first!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public LoginCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        throwIfUserLoggedIn();
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String Member = parameters.get(0);
        String Team = parameters.get(1);
        return login(Team, Member);
    }

    private String login(String team, String member) {
        Team teamFound = getTaskManagementSystemRepository().findTeamByName(team);
        Member memberFound = getTaskManagementSystemRepository().findMemberByName(member);
        if (!teamFound.getMembers().contains(memberFound)) {
            throw new InvalidUserInputException(WRONG_MEMBER_OR_TEAM);
        }
        getTaskManagementSystemRepository().login(memberFound);
        memberFound.addActivityHistory(format(MEMBER_LOGGED_IN, memberFound.getName()));
        return format(MEMBER_LOGGED_IN, memberFound.getName());
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private void throwIfUserLoggedIn() {
        if (getTaskManagementSystemRepository().hasLoggedInMember()) {
            throw new InvalidUserInputException(
                    format(MEMBER_LOGGED_IN_ALREADY,
                            getTaskManagementSystemRepository().getLoggedInMember().getName())
            );
        }
    }
}
