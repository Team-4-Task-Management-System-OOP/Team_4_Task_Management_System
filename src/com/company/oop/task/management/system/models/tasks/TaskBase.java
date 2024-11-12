package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Comment;
import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public abstract class TaskBase implements Task {

    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 100;
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;

    private static final String TITLE_VAL_ERR = format(
            "Title must be between %d and %d!",
            TITLE_MIN_LENGTH,
            TITLE_MAX_LENGTH);
    private static final String DESCRIPTION_VAL_ERR = format(
            "Description must be between %d and %d!",
            TITLE_MIN_LENGTH,
            TITLE_MAX_LENGTH);
    private static final String CANNOT_ADD_A_NULL_COMMENT = "Cannot add an empty comment.";
    private static final String NO_SUCH_COMMENT = "Comment not found.";
    private static final String CANNOT_ADD_A_NULL_HISTORY = "Cannot add an empty history.";
    private static final String COMMENT_REMOVED = "A comment was removed from item with ID: %d.";
    private static final String COMMENT_ADDED = "A comment was added to item with ID: %d.";
    private static final String NO_LOG_HISTORY = "---NO LOG HISTORY---";
    private static final String NO_COMMENTS = "---NO COMMENTS---";
    private static final String NO_COMMENTS_TO_REMOVE = "No comments available to remove.";
    private static final String NULL_COMMENT = "Cannot remove a null comment.";

    private final int id;
    private String title;
    private String description;
    private final List<Comment> comments;
    private final List<String> history;

    public TaskBase(int id, String title, String description){
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.comments = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, TITLE_VAL_ERR);
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        validateStringLength(description, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, DESCRIPTION_VAL_ERR);
        this.description = description;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    @Override
    public void addComment(Comment comment) {
        if (comment == null || comment.getContent() == null || comment.getContent().isEmpty()) {
            throw new InvalidUserInputException(CANNOT_ADD_A_NULL_COMMENT);
        } else {
            comments.add(comment);
            historyLogger(format(COMMENT_ADDED, getId()));
        }
    }

    // TODO - To consider if this functionality is going to be useful
//    @Override
//    public void removeComment(Comment comment) {
//        if (comment == null) {
//            throw new IllegalArgumentException(NULL_COMMENT);
//        }
//        if (comments == null || comments.isEmpty()) {
//            throw new InvalidUserInputException(NO_COMMENTS_TO_REMOVE);
//        }
//        if (comments.contains(comment)) {
//            comments.remove(comment);
//            historyLogger(format(COMMENT_REMOVED, getId()));
//        } else {
//            throw new InvalidUserInputException(NO_SUCH_COMMENT);
//        }
//    }

    @Override
    public void historyLogger (String log){
        if (log == null || log.isEmpty()) {
            throw new InvalidUserInputException(CANNOT_ADD_A_NULL_HISTORY);
        }
        history.add(log);
    }

    @Override
    public String printInfo(){
        return format("Id: %d%n" +
                "Title: %s%n", getId(), getTitle());
    }

    private String printComments() {
        if (getComments().isEmpty() || getComments() == null) {
            return NO_COMMENTS;
        } else {
            StringBuilder printComments = new StringBuilder();
            for (Comment comment : comments) {
                printComments.append(comment.toString()).append(System.lineSeparator());
            }
            return printComments.toString();
        }
    }

    private String printLogHistory() {
        if (getHistory().isEmpty() || getHistory() == null) {
            return NO_LOG_HISTORY;
        } else {
            StringBuilder logHistory = new StringBuilder();
            for (String log : history) {
                logHistory.append(log).append(System.lineSeparator());
            }
            return logHistory.toString();
        }
    }

    @Override
    public String toString() {
        return String.format("%s" +
                        "Description: %s%n" +
                        "Comments: %s%n" +
                        "History: %s%n",
                printInfo(), getDescription(), printComments(), printLogHistory());
    }

}
