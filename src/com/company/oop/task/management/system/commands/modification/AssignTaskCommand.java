package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

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

        String taskName = parameters.get(0);
        String memberName = parameters.get(1);
        String teamName = parameters.get(2);

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(NO_TEAMS_FOUND);
        }

        Member member = team.getMembers()
                .stream()
                .filter(m -> m.getName().equalsIgnoreCase(memberName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_MEMBERS_FOUND));

        Task task = team.getBoards()
                .stream()
                .flatMap(board -> board.getTasks().stream())
                .filter(t -> t.getTitle().equalsIgnoreCase(taskName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_TASKS_FOUND, taskName, taskName)));

        member.addTask(task);

        member.addActivityHistory(String.format(TASK_ASSIGNED_SUCCESSFUL_MESSAGE, task.getTitle()));
        return String.format(TASK_ASSIGNED, task.getId(), memberName);
    }
}
