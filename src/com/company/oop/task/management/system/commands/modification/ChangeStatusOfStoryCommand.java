package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.enums.StoryStatus;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.*;

public class ChangeStatusOfStoryCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

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

        String newStatus = parameters.get(0);
        String storyName = parameters.get(1);
        String boardName = parameters.get(2);
        String teamName = parameters.get(3);

        StoryStatus status;
        try {
            status = StoryStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(INVALID_STORY_STATUS_VALUE, newStatus));
        }

        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(NO_TEAMS_FOUND);
        }

        Board board = findBoardByName(team, boardName);
        Story story = findStoryByName(board, storyName);

        StoryStatus oldStatus = story.getStoryStatus();
        story.changeStoryStatus(status);

        story.historyLogger(String.format(
                STORY_STATUS_CHANGED, oldStatus, status, story.getId()));

        return String.format(SUCCESSFULLY_CHANGED_THE_STATUS_OF_STORY, storyName, status);
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
