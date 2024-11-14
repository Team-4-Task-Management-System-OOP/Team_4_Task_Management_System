package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.CommentImpl;
import com.company.oop.task.management.system.models.tasks.contracts.Comment;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

public class CreateCommentCommand extends BaseCommand{

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Please enter valid data.";
    public static final String COMMENT_ADDED_SUCCESSFULLY = "Comment added successfully by %s!";

    public CreateCommentCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String content = parameters.get(0);
        String authorName = parameters.get(1);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(2), INVALID_INPUT_MESSAGE);
        Task task = getTaskManagementSystemRepository().findTaskById(getTaskManagementSystemRepository().getTasks(), taskId);
        Comment comment = new CommentImpl(content, authorName);
        task.addComment(comment);

        return COMMENT_ADDED_SUCCESSFULLY;
    }
}
