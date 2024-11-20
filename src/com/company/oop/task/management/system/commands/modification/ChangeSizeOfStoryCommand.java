package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.StorySize;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;
import java.util.List;
import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangeSizeOfStoryCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public ChangeSizeOfStoryCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
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
        String newSize = parameters.get(1);
        String teamName = parameters.get(2);

        StorySize size;
        try {
            size = StorySize.valueOf(newSize.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(INVALID_SIZE_VALUE, newSize));
        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(NO_TEAMS_FOUND);
        }

        Story story = getTaskManagementSystemRepository().findTaskById(getTaskManagementSystemRepository().getStories(), storyId);

        StorySize oldSize = story.getStorySize();
        story.changeStorySize(size);

        story.historyLogger(String.format(SIZE_CHANGED, oldSize, size, story.getId()));
        return String.format(SUCCESSFULLY_CHANGED_THE_SIZE, story.getId() , size);
    }
}
