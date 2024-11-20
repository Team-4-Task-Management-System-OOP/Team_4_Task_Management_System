package com.company.oop.task.management.system.commands.utils;

public class CommandsConstants {

    public static final String MEMBER_ADDED = "Member \"%s\" was added to team \"%s.\"";

    public static final String FEEDBACK_CREATED = "Feedback with ID %d and title \"%s\" was created.";

    public static final String STORY_CREATED = "Story with ID %d and title \"%s\" was created.";

    public static final String BUG_CREATED = "Bug with ID %d and title \"%s\" was created.";

    public static final String INVALID_REPRODUCIBLE_STEPS = "Invalid value for steps to reproduce. " +
            "Should be a valid list of steps.";

    public static final String TEAM_HEADLINE = "---Team №%d---%n";

    public static final String MEMBER_HEADLINE = "---Member №%d---%n";

    public static final String BOARD_HEADLINE = "---Board №%d---%n";

    public static final String ALL_MEMBERS_MESSAGE = "---ALL MEMBERS---\n";

    public static final String ALL_MEMBERS_IN_TEAM_MESSAGE = "---ALL MEMBERS IN TEAM \"%s\"---\n";

    public static final String ALL_TEAMS_MESSAGE = "---ALL TEAMS---\n";

    public static final String ALL_TEAM_BOARDS_MESSAGE = "---ALL TEAM BOARDS---\n";

    public static final String MEMBER_CREATION_SUCCESSFUL_MESSAGE = "A member with name \"%s\" has been created!";

    public static final String TEAM_CREATION_SUCCESSFUL_MESSAGE = "A team with name \"%s\" has been created!";

    public static final String TASK_ASSIGNED_SUCCESSFUL_MESSAGE = "A task with name \"%s\" has been assigned!";

    public static final String TASK_UNASSIGNED_SUCCESSFUL_MESSAGE = "A task with name \"%s\" has been unassigned";

    public static final String INVALID_INPUT_MESSAGE = "Invalid input. " +
            "Please enter valid task ID.";

    public static final String VALID_TASK_ID = "Please, provide a valid task ID";

    public final static String USER_LOGGED_OUT = "Member \"%s\" logged out!";

    public final static String MEMBER_NOT_LOGGED = "You are not logged in! Please login first!";

    public static final String COMMENT_ADDED = "Comment was added successfully to task with title \"%s\" and ID %d. " +
            "Author of comment: \"%s\"";

    public static final String BOARD_CREATED_AND_ADDED = "Board with name \"%s\" " +
            "has been successfully created and added to team \"%s!\"";

    public static final String TASK_ASSIGNED = "Task with ID %d was assigned to \"%s\"";

    public static final String TASK_UNASSIGNED = "Task with ID %d was unassigned from \"%s\"";

    public static final String BUG_PRIORITY_CHANGED = "Bug priority changed";

    public static final String STORY_PRIORITY_CHANGED = "Story priority changed";

    public static final String INVALID_RATING_VALUE = "Invalid rating value. It must be an integer.";

    public static final String INVALID_SEVERITY_VALUE = "Invalid severity value: '%s'. " +
            "Valid values are: MINOR, MAJOR, CRITICAL.";

    public static final String INVALID_SIZE_VALUE = "Invalid size value: '%s'. " +
            "Valid values are: SMALL, MEDIUM, LARGE.";

    public static final String RATING_ERROR = "Rating must be between \"1\" and \"10\".";

    public static final String FEEDBACK_RATING_CHANGED = "Feedback rating changed from \"%d\" " +
            "to \"%d\" for Feedback with ID \"%s\".";

    public static final String SUCCESSFULLY_CHANGED_THE_RATING_OF_FEEDBACK = "Successfully changed " +
            "the rating of feedback '%s' to %d.";

    public static final String SIZE_CHANGED = "Story size changed from %s to %s for Story with ID %s.";

    public static final String SUCCESSFULLY_CHANGED_THE_SIZE = "Successfully changed the size of story '%s' to %s.";

    public static final String ENUM_CHANGED = "The priority of task ID \"%d\" was changed " +
            "from \"%s\" to \"%s\" successfully.";

    public static final String BUG_SEVERITY_CHANGED_FROM = "Bug severity changed from \"%s\" " +
            "to \"%s\" for Bug with ID %s.";

    public static final String SUCCESSFULLY_CHANGED_THE_SEVERITY_OF_BUG = "Successfully changed " +
            "the severity of bug \"%s\" to %s.\"";

    public static final String INVALID_BUG_STATUS_VALUE = "Invalid status value: '%s'. Valid values are: ACTIVE, FIXED.";

    public static final String INVALID_FEEDBACK_STATUS_VALUE = "Invalid status value: '%s'. Valid values are: NEW, UNSCHEDULED, SCHEDULED, DONE.";

    public static final String BUG_STATUS_CHANGED = "Bug status changed from %s to %s for Bug with ID %s.";

    public static final String FEEDBACK_STATUS_CHANGED = "Feedback status changed from %s to %s for Feedback with ID %s.";

    public static final String INVALID_STORY_STATUS_VALUE = "Invalid story status value: '%s'. Valid values are: NOT_DONE, IN_PROGRESS, DONE.";

    public static final String STORY_STATUS_CHANGED = "Story status changed from %s to %s for Story with ID %s.";

    public static final String SUCCESSFULLY_CHANGED_THE_STATUS_OF_STORY = "Successfully changed the status of story '%s' to %s.";

    public static final String SUCCESSFULLY_CHANGED_THE_STATUS_OF_BUG = "Successfully changed the status of bug '%s' to %s.";

    public static final String SUCCESSFULLY_CHANGED_THE_STATUS_OF_FEEDBACK = "Successfully changed the status of feedback '%s' to %s.";

    public static final String BOARD_DOES_NOT_EXIST_IN_TEAM = "Board with name \"%s\" " +
            "doesn't exist in team \"%s\"";

    public static final String NO_MEMBERS_FOUND = "There are no members in team \"%s\" to be shown. " +
            "Please add a member first.";
    public static final String NO_MEMBERS_FOUND_GENERAL_ERR_MSG = "There are no members in the system. " +
            "Please add a member first.";

    public static final String CANNOT_UNASSIGN_FEEDBACK = "Cannot unassign a feedback to member \"%s\", " +
            "because feedback is not an assignable Task!";

    public static final String CANNOT_ASSIGN_FEEDBACK = "Cannot assign a feedback to member \"%s\", " +
            "because feedback is not an assignable Task!";

    public static final String NO_TEAMS_FOUND = "There are no teams to be shown. " +
            "Please add a team first.";

    public static final String NO_MEMBERS_FOUND_IN_TEAM = "There are no members in team \"%s\" to be shown. " +
            "Please add a team first.";

    public static final String NO_BOARDS_FOUND = "There are no boards in team \"%s\" to be shown. " +
            "Please add a board to team \"%s\" first.";

    public static final String NO_TASK_FOUND = "There is no task with id \"%d\" found!";

    public static final String NO_REGISTERED_TASKS = "There are no registered tasks.";

    public static final String NO_REGISTERED_BUGS = "There are no registered bugs.";

    public static final String NO_REGISTERED_FEEDBACKS = "There are no registered feedbacks.";

    public static final String NO_REGISTERED_STORIES = "There are no registered stories.";

    public static final String NO_REGISTERED_ASSIGNED_TASKS = "There are no assigned tasks.";

    public static final String MEMBER_ALREADY_IN_TEAM = "The member is already in the team!";

    public static final String JOIN_DELIMITER = "####################";

}
