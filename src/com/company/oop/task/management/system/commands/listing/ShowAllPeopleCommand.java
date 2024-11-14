package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Member;

import java.util.List;

public class ShowAllPeopleCommand extends BaseCommand implements Command {

    public static final String NO_MEMBERS_FOUND_IN_THE_TEAM = "No members found in the team.";

    public ShowAllPeopleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        List<Member> members = getTaskManagementSystemRepository().getMembers();

        if (members.isEmpty()) {
            return NO_MEMBERS_FOUND_IN_THE_TEAM;
        }
        StringBuilder membersInfo = new StringBuilder("Team Members:\n");
        for (Member member : members) {
            membersInfo.append("Name: ")
                    .append(member.getName())
                    .append(", Tasks: ")
                    .append(member.getTasks().size())
                    .append(", Activity History: ")
                    .append(member.getActivityHistory().size())
                    .append(" entries\n");
        }
        return membersInfo.toString();
    }
}
