package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.MEMBER_CREATION_SUCCESSFUL_MESSAGE;

public class CreateMemberCommand extends BaseCommand {

    // Constants
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public CreateMemberCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String memberName = parameters.get(0);
        Member currentMember = getTaskManagementSystemRepository().createMember(memberName);
        currentMember.addActivityHistory(String.format(MEMBER_CREATION_SUCCESSFUL_MESSAGE, memberName));
        return String.format(MEMBER_CREATION_SUCCESSFUL_MESSAGE, currentMember.getName());
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
}
