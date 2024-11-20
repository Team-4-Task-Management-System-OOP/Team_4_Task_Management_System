package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Assignable;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;
import static java.lang.String.format;

public class AssignTaskCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public AssignTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int iD = ParsingHelpers.tryParseInt(parameters.get(0), VALID_TASK_ID);
        String memberName = parameters.get(1);
        String teamName = parameters.get(2);

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new ElementNotFoundException(NO_TEAMS_FOUND);
        }

        Member member = team.getMembers()
                .stream()
                .filter(m -> m.getName().equalsIgnoreCase(memberName))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(format(NO_MEMBERS_FOUND, team.getName())));

        Assignable task = team.getBoards()
                .stream()
                .flatMap(board -> board.getAssignableTasks().stream())
                .filter(t -> t.getId() == (iD))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(format(NO_TASK_FOUND, iD)));

        member.addTask(task);
        task.assignMember(member);
        member.addActivityHistory(format(TASK_ASSIGNED_SUCCESSFUL_MESSAGE, task.getTitle()));
        return format(TASK_ASSIGNED, task.getId(), memberName);
    }
}
