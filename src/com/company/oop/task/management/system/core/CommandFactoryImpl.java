package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.commands.creation.*;
import com.company.oop.task.management.system.commands.enums.CommandType;
import com.company.oop.task.management.system.commands.listing.*;
import com.company.oop.task.management.system.commands.modification.*;
import com.company.oop.task.management.system.commands.security.LoginCommand;
import com.company.oop.task.management.system.commands.security.LogoutCommand;
import com.company.oop.task.management.system.core.contracts.CommandFactory;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case CREATEMEMBER:
                return new CreateMemberCommand(taskManagementSystemRepository);
            case SHOWALLPEOPLE:
                return new ShowAllPeopleCommand(taskManagementSystemRepository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonActivityCommand(taskManagementSystemRepository);
            case CREATETEAM:
                return new CreateTeamCommand(taskManagementSystemRepository);
            case CREATECOMMENT:
                return new CreateCommentCommand(taskManagementSystemRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeamCommand(taskManagementSystemRepository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivityCommand(taskManagementSystemRepository);
            case ADDPERSONTOTEAM:
                return new AddPersonToTeamCommand(taskManagementSystemRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembersCommand(taskManagementSystemRepository);
            case CREATEBOARD:
                return new CreateNewBoardInTeamCommand(taskManagementSystemRepository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoardsCommand(taskManagementSystemRepository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivityCommand(taskManagementSystemRepository);
            case CREATEBUG:
                return new CreateBugInBoardCommand(taskManagementSystemRepository);
            case CREATESTORY:
                return new CreateStoryInBoardCommand(taskManagementSystemRepository);
            case CREATEFEEDBACK:
                return new CreateFeedbackInBoardCommand(taskManagementSystemRepository);
            case CHANGEPRIORITYOFBUG:
                return new ChangePriorityOfBugCommand(taskManagementSystemRepository);
            case CHANGESEVERITYOFBUG:
                return new ChangeSeverityOfBugCommand(taskManagementSystemRepository);
            case CHANGESTATUSOFBUG:
                return new ChangeStatusOfBugCommand(taskManagementSystemRepository);
            case CHANGEPRIORITYOFSTORY:
                return new ChangePriorityOfStoryCommand(taskManagementSystemRepository);
            case CHANGESIZEOFSTORY:
                return new ChangeSizeOfStoryCommand(taskManagementSystemRepository);
            case CHANGESTATUSOFSTORY:
                return new ChangeStatusOfStoryCommand(taskManagementSystemRepository);
            case CHANGERATINGOFFEEDBACK:
                return new ChangeRatingOfFeedbackCommand(taskManagementSystemRepository);
            case CHANGESTATUSOFFEEDBACK:
                return new ChangeStatusOfFeedbackCommand(taskManagementSystemRepository);
            case ASSIGNTASK:
                return new AssignTaskCommand(taskManagementSystemRepository);
            case UNASSIGNTASK:
                return new UnassignTaskCommand(taskManagementSystemRepository);
            case ADDCOMMENTTOTASK:
                return new AddCommentToTaskCommand(taskManagementSystemRepository);
            case LISTALLTASKS:
                return new ListAllTasksCommand(taskManagementSystemRepository);
            case FILTERTASKSBYTITLE:
                return new FilterTasksByTitleCommand(taskManagementSystemRepository);
            case SORTTASKBYTITLE:
                return new SortTasksByTitleCommand(taskManagementSystemRepository);
            case LISTBUGS:
                return new ListBugsCommand(taskManagementSystemRepository);
            case LISTSTORIES:
                return new ListStoriesCommand(taskManagementSystemRepository);
            case LISTFEEDBACKS:
                return new ListFeedbacksCommand(taskManagementSystemRepository);
            case LISTASSIGNEDTASKS:
                return new ListAssignedTasksCommand(taskManagementSystemRepository);
            case FILTERTASKSBYSTATUS:
                return new FilterTasksByStatusCommand(taskManagementSystemRepository);
            case FILTERTASKSBYASSIGNEE:
                return new FilterTasksByAssigneeCommand(taskManagementSystemRepository);
            case SORTTASKS:
                return new SortTasksCommand(taskManagementSystemRepository);
            case LOGIN:
                return new LoginCommand(taskManagementSystemRepository);
            case LOGOUT:
                return new LogoutCommand(taskManagementSystemRepository);
            default:
                throw new InvalidUserInputException(String.format(INVALID_COMMAND, commandType));
        }
    }
}
