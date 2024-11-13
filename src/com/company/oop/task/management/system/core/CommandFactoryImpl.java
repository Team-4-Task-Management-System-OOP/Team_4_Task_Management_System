package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.commands.*;
import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.commands.enums.CommandType;
import com.company.oop.task.management.system.core.contracts.CommandFactory;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case CREATEPERSON:
                return new CreatePersonCommand(taskManagementSystemRepository);
            case SHOWALLPEOPLE:
                return new ShowAllPeopleCommand(taskManagementSystemRepository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonActivityCommand(taskManagementSystemRepository);
            case CREATETEAM:
                return new CreateTeamCommand(taskManagementSystemRepository);
            case SHOWALLTEAM:
                return new ShowAllTeamCommand(taskManagementSystemRepository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivityCommand(taskManagementSystemRepository);
            case ADDPERSONTOTEAM:
                return new AddPersonToTeamCommand(taskManagementSystemRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembersCommand(taskManagementSystemRepository);
            case CREATENEWBOARDINTEAM:
                return new CreateNewBoardInTeamCommand(taskManagementSystemRepository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoardsCommand(taskManagementSystemRepository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivityCommand(taskManagementSystemRepository);
            case CREATEBUGINBOARD:
                return new CreateBugInBoardCommand(taskManagementSystemRepository);
            case CREATESTORYINBOARD:
                return new CreateStoryInBoardCommand(taskManagementSystemRepository);
            case CREATEFEEDBACKINBOARD:
                return new CreateFeedbackInBoardCommand(taskManagementSystemRepository);
            case CHANGEPRIORITYOFBUG:
                return new ChangePriorityOfBugCommand(taskManagementSystemRepository);
            case CHAGESEVERITYOGBUG:
                return new ChangeSeverityOfBugCommand(taskManagementSystemRepository);
            case CHAGESTATUSOFBUG:
                return new ChangeStatusOfBugCommand(taskManagementSystemRepository);
            case CHANGEPRIORITYOFSTORY:
                return new ChangePriorityOfStoryCommand(taskManagementSystemRepository);
            case CHAGESIZEOFSTORY:
                return new ChangeSizeOfStoryCommand(taskManagementSystemRepository);
            case CHAGESTATUSOFSTORY:
                return new ChangeStatusOfStoryCommand(taskManagementSystemRepository);
            case CHAGERATINGOFFEEDBACK:
                return new ChangeRatingOfFeedbackCommand(taskManagementSystemRepository);
            case CHAGESTATUSOFFEEDBACK:
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
            case LISTFEEDBACK:
                return new ListFeedbackCommand(taskManagementSystemRepository);
            case FILTERTASKSBYSTATUS:
                return new FilterTasksByStatusCommand(taskManagementSystemRepository);
            case FILTERTASKSBYASSIGNEE:
                return new FilterTasksByAssigneeCommand(taskManagementSystemRepository);
            case SORTTASKS:
                return new SortTasksCommand(taskManagementSystemRepository);
            default:
                throw new IllegalArgumentException("todo");
        }
    }
}
