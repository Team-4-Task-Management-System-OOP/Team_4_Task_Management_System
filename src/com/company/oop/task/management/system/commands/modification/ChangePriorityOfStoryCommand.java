package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangePriorityOfStoryCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String INVALID_PRIORITY_VALUE = "Invalid priority value: '%s'. Valid values are: LOW, MEDIUM, HIGH.";

    public ChangePriorityOfStoryCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int storyId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_INPUT_MESSAGE);
        String newPriority = parameters.get(1);
        String teamName = parameters.get(2);

        PriorityType priority;
        try {
            priority = PriorityType.valueOf(newPriority.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(INVALID_PRIORITY_VALUE, newPriority));
        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(String.format(NO_TEAMS_FOUND));
        }

        Story story = getTaskManagementSystemRepository().findTaskById(getTaskManagementSystemRepository().getStories(), storyId);

        story.changePriority(priority);

        story.historyLogger(String.format(ENUM_CHANGED, story.getId(), priority, newPriority));
        return String.format(STORY_PRIORITY_CHANGED);
    }
}
