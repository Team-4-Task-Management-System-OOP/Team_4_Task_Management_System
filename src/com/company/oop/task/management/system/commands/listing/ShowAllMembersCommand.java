package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;
import static java.lang.String.format;

public class ShowAllMembersCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ShowAllMembersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        List<Member> allMembers = getTaskManagementSystemRepository().getMembers();
        if (allMembers.isEmpty()) {
            return NO_MEMBERS_FOUND;
        }
        else {
            StringBuilder allMembersPrint = new StringBuilder();
            allMembersPrint.append(ALL_MEMBERS_MESSAGE);
            for (int i = 0; i < allMembers.size(); i++) {
                allMembersPrint.append(format(MEMBER_HEADLINE, i + 1)).append(allMembers.get(i).toString());
                if (i < allMembers.size() - 1) {
                    allMembersPrint.append(System.lineSeparator());
                    allMembersPrint.append(JOIN_DELIMITER).append(System.lineSeparator());
                }
            }
            return allMembersPrint.toString();
        }
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
}
