package com.company.oop.task.management.system.commands.modification;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.contracts.Comment;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.utils.ParsingHelpers;
import com.company.oop.task.management.system.utils.ValidationHelpers;

import java.util.List;

public class AddCommentToTaskCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    public final static String COMMENT_ADDED_SUCCESSFULLY = "%s added comment successfully!";
    public final static String TASK_DOES_NOT_EXIST = "The task you are trying to add a comment to does not exist!";
    public final static String TASK_TYPE_DOES_NOT_EXIST = "The task type does not exist! Valid task types are:" +
            "Bug, Story, Feedback";

    public AddCommentToTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String content = parameters.get(0);
        String author = parameters.get(1);
        TaskType taskType = ParsingHelpers.tryParseEnum(parameters.get(2).toUpperCase(), TaskType.class);
        String taskName = parameters.get(3);

        return addComment(content, author );
    }

    private String addComment(String content, String author) {

        Member member = getTaskManagementSystemRepository().findMemberByName(author);

        Task task = member.getTasks().stream()
                .filter(t -> t.getType().equalsIgnoreCase(taskType) && t.getName().equalsIgnoreCase(taskName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(TASK_DOES_NOT_EXIST));


        Vehicle vehicle = user.getVehicles().get(vehicleIndex);

        Comment comment = getTaskManagementSystemRepository().createComment(content, getTaskManagementSystemRepository().getLoggedInMember().getName());

        getTaskManagementSystemRepository().getLoggedInMember().addComment(comment, vehicle);

        return String.format(COMMENT_ADDED_SUCCESSFULLY, getVehicleDealershipRepository().getLoggedInUser().getUsername());
    }
}
