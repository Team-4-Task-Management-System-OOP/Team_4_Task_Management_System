package com.company.oop.task.management.system.commands.listing;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

public class ShowBoardActivityCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowBoardActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Board boardToShowActivity = getTaskManagementSystemRepository().findBoardByName(parameters.get(0));
        return boardToShowActivity.printHistory();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
