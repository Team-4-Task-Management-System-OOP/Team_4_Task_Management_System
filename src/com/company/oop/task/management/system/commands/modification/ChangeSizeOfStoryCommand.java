package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.StorySize;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;
import java.util.List;
import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangeSizeOfStoryCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

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

        String newSize = parameters.get(0);
        String storyName = parameters.get(1);
        String boardName = parameters.get(2);
        String teamName = parameters.get(3);

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

        Board board = findBoardByName(team, boardName);
        Story story = findStoryByName(board, storyName);

        StorySize oldSize = story.getStorySize();
        story.changeStorySize(size);
        story.historyLogger(String.format(SIZE_CHANGED, oldSize, size, story.getId()));

        return String.format(SUCCESSFULLY_CHANGED_THE_SIZE, storyName, size);
    }

    private Board findBoardByName(Team team, String boardName) {
        return team.getBoards()
                .stream()
                .filter(board -> board.getName().equalsIgnoreCase(boardName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(NO_BOARDS_FOUND, boardName, team.getName())));
    }

    private Story findStoryByName(Board board, String storyName) {
        return board.getTasks()
                .stream()
                .filter(task -> task instanceof Story)
                .map(task -> (Story) task)
                .filter(story -> story.getTitle().equalsIgnoreCase(storyName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_REGISTERED_STORIES));
    }
}
