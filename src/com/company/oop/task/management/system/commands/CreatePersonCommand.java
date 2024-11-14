package com.company.oop.task.management.system.commands;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.sql.SQLOutput;
import java.util.List;

public class CreatePersonCommand extends BaseCommand {

    // Constants
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String MEMBER_CREATION_SUCCESSFUL_MESSAGE = "A member with name %s has been created!";

    public CreatePersonCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
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
        currentMember.addActivity(String.format(MEMBER_CREATION_SUCCESSFUL_MESSAGE, name));
    }
}
