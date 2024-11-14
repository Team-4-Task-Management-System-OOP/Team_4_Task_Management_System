package com.company.oop.task.management.system.commands.security;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

public class LoginCommand extends BaseCommand {

    private final static String MEMBER_LOGGED_IN = "Member %s successfully logged in!";
    private final static String WRONG_MEMBER_OR_TEAM = "Your team and member's name does not match!" +
            " Please, first provide a team, then its member to proceed!";
    public final static String MEMBER_LOGGED_IN_ALREADY = "Member %s is logged in! Please log out first!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public LoginCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        throwIfUserLoggedIn();
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String foundTeam = parameters.get(0);
        String foundMember = parameters.get(1);

        return login(foundTeam, foundMember);
    }

    private String login(String foundTeam, String foundMember) {

        Team teamFound = getTaskManagementSystemRepository().findTeamByName(foundMember);
        Member memberFound = getTaskManagementSystemRepository().findMemberByName(foundMember);

        //ToDo
        //findteambyname and findmemberbyname check before checking them if they are in the team!!!!

        if (!teamFound.getMembers().contains(memberFound)) {
            throw new InvalidUserInputException(WRONG_MEMBER_OR_TEAM);
        }
        // Login Java Pesho
        getTaskManagementSystemRepository().login(memberFound);
        return String.format(MEMBER_LOGGED_IN, memberFound.getName());
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private void throwIfUserLoggedIn() {
        if (getTaskManagementSystemRepository().hasLoggedInMember()) {
            throw new InvalidUserInputException(
                    String.format(MEMBER_LOGGED_IN_ALREADY,
                            getTaskManagementSystemRepository().getLoggedInMember().getName())
            );
        }
    }
}
