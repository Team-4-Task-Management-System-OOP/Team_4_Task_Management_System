package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.StoryStatus;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangeStatusOfStoryCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public ChangeStatusOfStoryCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
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
        String newStatus = parameters.get(1);
        String teamName = parameters.get(2);

        StoryStatus status;
        try {
            status = StoryStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUserInputException(String.format(INVALID_STORY_STATUS_VALUE, newStatus));
        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new InvalidUserInputException(NO_TEAMS_FOUND);
        }

        Story story = getTaskManagementSystemRepository().findTaskById(getTaskManagementSystemRepository().getStories(), storyId);

        StoryStatus oldStatus = story.getStoryStatus();
        story.changeStoryStatus(status);

        story.historyLogger(String.format(
                STORY_STATUS_CHANGED, oldStatus, status, story.getId()));

        return String.format(SUCCESSFULLY_CHANGED_THE_STATUS_OF_STORY, story.getId(), status);
    }
}
