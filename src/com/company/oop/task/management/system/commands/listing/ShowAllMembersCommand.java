package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.NO_PERSONS_FOUND_IN_THE_MEMBERS;

public class ShowAllMembersCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ShowAllMembersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<Member> allMembers = getTaskManagementSystemRepository().getMembers();

        if (allMembers.isEmpty()) {
            return NO_PERSONS_FOUND_IN_THE_MEMBERS;
        }
        StringBuilder allMembersPrint = new StringBuilder();
        for (Member member : allMembers) {
            allMembersPrint.append(member.getName()).append(System.lineSeparator());
        }

//        StringBuilder membersInfo = new StringBuilder("Team Members:\n");
//        for (Member member : allMembers) {
//            membersInfo.append("Name: ")
//                    .append(member.getName())
//                    .append(", Tasks: ")
//                    .append(member.getTasks().size())
//                    .append(", Activity History: ")
//                    .append(member.getActivityHistory().size())
//                    .append(" entries\n");
//        }
        return allMembersPrint.toString();
    }
}
