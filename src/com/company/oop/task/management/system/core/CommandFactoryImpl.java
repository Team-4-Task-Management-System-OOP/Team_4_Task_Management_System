package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.commands.creation.*;
import com.company.oop.task.management.system.commands.enums.CommandType;
import com.company.oop.task.management.system.commands.listing.*;
import com.company.oop.task.management.system.commands.modification.*;
import com.company.oop.task.management.system.commands.security.LoginCommand;
import com.company.oop.task.management.system.commands.security.LogoutCommand;
import com.company.oop.task.management.system.commands.utils.HelpCommand;
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
            case SHOWALLMEMBERS:
                return new ShowAllMembersCommand(taskManagementSystemRepository);
            case SHOWMEMBERACTIVITY:
                return new ShowMemberActivityCommand(taskManagementSystemRepository);
            case CREATETEAM:
                return new CreateTeamCommand(taskManagementSystemRepository);
            case ADDCOMMENT:
                return new AddCommentToTask(taskManagementSystemRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeamsCommand(taskManagementSystemRepository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivityCommand(taskManagementSystemRepository);
            case ADDMEMBERTOTEAM:
                return new AddMemberToTeamCommand(taskManagementSystemRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembersCommand(taskManagementSystemRepository);
            case CREATEBOARD:
                return new CreateBoardInTeamCommand(taskManagementSystemRepository);
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
            case SHOWALLTASKS:
                return new ShowAllTasksCommand(taskManagementSystemRepository);
            case SHOWALLTASKSFILTEREDBYTITLE:
                return new FilterAllTasksByTitleCommand(taskManagementSystemRepository);
            case SHOWALLBUGS:
                return new ShowAllBugsCommand(taskManagementSystemRepository);
            case SHOWALLSTORIES:
                return new ShowAllStoriesCommand(taskManagementSystemRepository);
            case SHOWALLFEEDBACKS:
                return new ShowAllFeedbacksCommand(taskManagementSystemRepository);
            case SHOWALLASSIGNEDTASKSSORTEBYTITLE:
                return new ShowAllAssignedTasksSortedByTitle(taskManagementSystemRepository);
            case SHOWALLASSIGNEDTASKS:
                return new ShowAllAssignedTasks(taskManagementSystemRepository);
            case FILTERTASKSBYSTATUS:
                return new FilterTasksByStatusCommand(taskManagementSystemRepository);
            case FILTERTASKSBYASSIGNEE:
                return new FilterTasksByAssigneeCommand(taskManagementSystemRepository);
            case SHOWALLTASKSSORTEBYTITLE:
                return new SortAllTasksByTitleCommand(taskManagementSystemRepository);
            case SHOWALLTASKSFILTEREDANDSORTEBYTITLE:
                return new SortAndFilterAllTasksByTitleCommand(taskManagementSystemRepository);
            case LOGIN:
                return new LoginCommand(taskManagementSystemRepository);
            case LOGOUT:
                return new LogoutCommand(taskManagementSystemRepository);
            case HELP:
                return new HelpCommand(taskManagementSystemRepository);
            default:
                throw new InvalidUserInputException(String.format(INVALID_COMMAND, commandType));
        }
    }
}
