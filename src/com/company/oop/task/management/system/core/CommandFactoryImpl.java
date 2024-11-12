package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.commands.*;
import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.commands.enums.CommandType;
import com.company.oop.task.management.system.core.contracts.CommandFactory;
import com.company.oop.task.management.system.core.contracts.TaskManagementRepository;
import com.company.oop.task.management.system.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case CREATEPERSON:
                return new CreatePersonCommand(taskManagementRepository);
            case SHOWALLPEOPLE:
                return new ShowAllPeopleCommand(taskManagementRepository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonActivityCommand(taskManagementRepository);
            case CREATETEAM:
                return new CreateTeamCommand(taskManagementRepository);
            case SHOWALLTEAM:
                return new ShowAllTeamCommand(taskManagementRepository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivityCommand(taskManagementRepository);
            case ADDPERSONTOTEAM:
                return new AddPersonToTeamCommand(taskManagementRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembersCommand(taskManagementRepository);
            case CREATENEWBOARDINTEAM:
                return new CreateNewBoardInTeamCommand(taskManagementRepository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoardsCommand(taskManagementRepository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivityCommand(taskManagementRepository);
            case CREATEBUGINBOARD:
                return new CreateBugInBoardCommand(taskManagementRepository);
            case CREATESTORYINBOARD:
                return new CreateStoryInBoardCommand(taskManagementRepository);
            case CREATEFEEDBACKINBOARD:
                return new CreateFeedbackInBoardCommand(taskManagementRepository);
            case CHANGEPRIORITYOFBUG:
                return new ChangePriorityOfBugCommand(taskManagementRepository);
            case CHAGESEVERITYOGBUG:
                return new ChangeSeverityOfBugCommand(taskManagementRepository);
            case CHAGESTATUSOFBUG:
                return new ChangeStatusOfBugCommand(taskManagementRepository);
            case CHANGEPRIORITYOFSTORY:
                return new ChangePriorityOfStoryCommand(taskManagementRepository);
            case CHAGESIZEOFSTORY:
                return new ChangeSizeOfStoryCommand(taskManagementRepository);
            case CHAGESTATUSOFSTORY:
                return new ChangeStatusOfStoryCommand(taskManagementRepository);
            case CHAGERATINGOFFEEDBACK:
                return new ChangeRatingOfFeedbackCommand(taskManagementRepository);
            case CHAGESTATUSOFFEEDBACK:
                return new ChangeStatusOfFeedbackCommand(taskManagementRepository);
            case ASSIGNTASK:
                return new AssignTaskCommand(taskManagementRepository);
            case UNASSIGNTASK:
                return new UnassignTaskCommand(taskManagementRepository);
            case ADDCOMMENTTOTASK:
                return new AddCommentToTaskCommand(taskManagementRepository);
            case LISTALLTASKS:
                return new ListAllTasksCommand(taskManagementRepository);
            case FILTERTASKSBYTITLE:
                return new FilterTasksByTitleCommand(taskManagementRepository);
            case SORTTASKBYTITLE:
                return new SortTasksByTitleCommand(taskManagementRepository);
            case LISTBUGS:
                return new ListBugsCommand(taskManagementRepository);
            case LISTSTORIES:
                return new ListStoriesCommand(taskManagementRepository);
            case LISTFEEDBACK:
                return new ListFeedbackCommand(taskManagementRepository);
            case FILTERTASKSBYSTATUS:
                return new FilterTasksByStatusCommand(taskManagementRepository);
            case FILTERTASKSBYASSIGNEE:
                return new FilterTasksByAssigneeCommand(taskManagementRepository);
            case SORTTASKS:
                return new SortTasksCommand(taskManagementRepository);
            default:
                throw new IllegalArgumentException();
        }
    }
}
