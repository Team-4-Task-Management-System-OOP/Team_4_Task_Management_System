package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.MemberImpl;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

public class ShowPersonActivityCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowPersonActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Member memberToShowActivity = getTaskManagementSystemRepository().findMemberByName(parameters.get(0));
        return memberToShowActivity.printHistory();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}