package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.StorySize;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.BOARD_DOES_NOT_EXIST_IN_TEAM;
import static com.company.oop.task.management.system.commands.utils.CommandsConstants.STORY_CREATED;
import static java.lang.String.format;

public class CreateStoryInBoardCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    public CreateStoryInBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String description = parameters.get(1);
        PriorityType storyPriorityType = ParsingHelpers.tryParseEnum(parameters.get(2), PriorityType.class);
        StorySize storySize = ParsingHelpers.tryParseEnum(parameters.get(3), StorySize.class);
        String boardNameToAddStoryIn = parameters.get(4);
        Team loggedInTeam = getTaskManagementSystemRepository().getLoggedInTeam();
        boolean boardExistsInTeam = loggedInTeam.getBoards()
                .stream()
                .anyMatch(board -> board.getName().equalsIgnoreCase(boardNameToAddStoryIn));

        if (!boardExistsInTeam) {
            throw new InvalidUserInputException(format(BOARD_DOES_NOT_EXIST_IN_TEAM,
                    boardNameToAddStoryIn,
                    loggedInTeam.getName()));
        }
        Board boardToAddStoryIn = getTaskManagementSystemRepository().findBoardByName(boardNameToAddStoryIn);

        Story story = getTaskManagementSystemRepository()
                .createStory(title, description, storyPriorityType, storySize);
        boardToAddStoryIn.addTask(story);
        story.historyLogger(format(STORY_CREATED, story.getId(), story.getTitle()));
        return format(STORY_CREATED, story.getId(), story.getTitle());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
