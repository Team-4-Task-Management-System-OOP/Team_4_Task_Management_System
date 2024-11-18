package com.company.oop.task.management.system.commands.creation;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.CommentImpl;
import com.company.oop.task.management.system.models.tasks.contracts.Comment;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.COMMENT_ADDED;
import static com.company.oop.task.management.system.commands.utils.CommandsConstants.INVALID_INPUT_MESSAGE;
import static java.lang.String.format;

public class AddCommentToTask extends BaseCommand{

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public AddCommentToTask(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String content = parameters.get(0);
        String authorName = parameters.get(1);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(2), INVALID_INPUT_MESSAGE);
        Task task = getTaskManagementSystemRepository()
                .findTaskById(getTaskManagementSystemRepository().getTasks(), taskId);
        Comment comment = new CommentImpl(content, authorName);
        task.addComment(comment);
        task.historyLogger(format(COMMENT_ADDED, task.getTitle(), taskId, authorName));
        return format(COMMENT_ADDED, task.getTitle(), taskId, authorName);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
}
