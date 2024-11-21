package com.company.oop.task.management.system.commands.utils;

import com.company.oop.task.management.system.commands.BaseCommand;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class HelpCommand extends BaseCommand {

    private static final String commands = """
            Welcome to the Task application of Ivan, Viktor and Dimitar!
            Below is a list of available commands:
            
            1) showallmembers --> Displays all member registered in the system.
            2) showallteammembers {{nameOfTheTeam}} --> Displays all members from the current team.
            2) showallteams --> Displays all teams created in the system.
            3) showteamactivity {{nameOfTheTeam}} --> Displays the activity history for the current team.
            4) showmemberactivity {{nameOfTheMember}} --> Displays the activity history for the current member.
            5) showboardactivity {{nameOfTheBoard}} --> Displays the activity history for the current board.
            6) showallteamboards {{nameOfTheTeam}} --> Displays all board of a current team.
            7) showallteammembers {{nameOfTHeTeam}} --> Displays all team members from the current team.
            8) createmember {{nameOfTheMember}}  --> Receive a member's name and create such a member.
            9) createteam {{nameOfTheTeam}}  --> Receive a team's name and create such a team.
            10) createboard {{nameOfTheBoard}}  --> Receive a board's name and create such a board.
            11) createfeedback {{title}} {{description}} {{rating}} {{boardNameToAddFeedbackIn}}
            The {{title and the description have to be wrapped in double quotes\
             --> Receive a feedback's name, feedback's description, feedback's rating, board name where the feedback to be added and team's name and add the feedback to the team.
            12) createstory {{title}} {{description}} {{priority}} {{size}} {{boardNameToAddFeedbackIn}}
            The {{title}} and the {{description}} have to be wrapped in double quotes.
            The {{priority}} has to be one between 1)HIGH 2)MEDIUM 3)LOW
            The {{size}} has to be one between 1)LARGE 2)MEDIUM 3)SMALL
              --> Receive a story's name, story's description, story's priority, story's size, board name where the story to be added and create such a story.
            13) createbug {{title}} {{description}} {{reproducibleSteps}} {{priority}} {{severity}} {{boardNameToAddFeedbackIn}}
            The {{title}} and the {{description}} have to be wrapped in double quotes.
            The {{priority}} has to be one between 1)HIGH 2)MEDIUM 3)LOW
            The {{reproducibleSteps}} have to be separated by a comma
            The {{severity}} has to be one between: 1)CRITICAL 2)MAJOR 3)MINOR
              --> Receive a bug's name, bug's description, bug's reproducibleSteps, bug's priority, bug's severity, board name where the bug to be added and create such a bug.
            14) addmembertoteam {{nameOfTheMemberToBeAdded}} {{nameToTeamToBeAddedIn}} --> Receive a member's name and team's name and add the member to the team.
            15) addcomment {{comment}} {{authorName}} {{taskId}}}\
             --> Receive a comment, author's name and task ID and add comment for this task.
            15) assigntask {{taskId}} {{memberName}} {{teamName}}}\
             --> Receive a taskId, member's name and team's name and assign the task to the member
            16) unassigntask {{taskId}} {{memberName}} {{teamName}}}\
             --> Receive a taskId, member's name and unassign the current tasks
            """;





    public HelpCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return commands;
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }
}
