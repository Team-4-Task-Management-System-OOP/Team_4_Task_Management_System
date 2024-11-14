package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

public class CreateMemberCommand extends BaseCommand {

    // Constants
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String MEMBER_CREATION_SUCCESSFUL_MESSAGE = "A member with name %s has been created!";

    public CreateMemberCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String name = parameters.getFirst();

        createPerson(name);

        return String.format(MEMBER_CREATION_SUCCESSFUL_MESSAGE, name);
    }

    private void createPerson(String name) {
        Member currentMember = getTaskManagementSystemRepository().createMember(name);
        currentMember.addActivityHistory(String.format(MEMBER_CREATION_SUCCESSFUL_MESSAGE, name));
    }
    // you dont need to be logged in to create a member
    @Override
    protected boolean requiresLogin() {
        return false;
    }
}
