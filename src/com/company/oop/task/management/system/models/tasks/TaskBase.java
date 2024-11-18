package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Comment;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.utils.ParsingHelpers.formatTime;
import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public abstract class TaskBase implements Task {

    //Constants
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
            DESCRIPTION_MIN_LENGTH,
            DESCRIPTION_MAX_LENGTH);
    private static final String CANNOT_ADD_A_NULL_COMMENT = "Cannot add an empty comment.";
    private static final String COMMENT_ADDED = "A comment was added to item with ID: %d.";
    private static final String NO_LOG_HISTORY = "---NO LOG HISTORY IN TASK TO DISPLAY---";
    private static final String NO_COMMENTS = "---NO COMMENTS ADDED TO TASK TO DISPLAY---";


    private final int id;
    private String title;
    private String description;
    private final List<Comment> comments;
    private final List<String> history;

    public TaskBase(int id, String title, String description) {
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

    public abstract TaskType getTaskType();

    @Override
    public void addComment(Comment comment) {
        if (comment == null || comment.getContent() == null || comment.getContent().isEmpty()) {
            throw new InvalidUserInputException(CANNOT_ADD_A_NULL_COMMENT);
        } else {
            comments.add(comment);
            historyLogger(format(COMMENT_ADDED, getId()));
        }
    }

    @Override
    public void historyLogger(String log) {
        history.add(format("[%s] - %s", formatTime(LocalDateTime.now()), log));
    }

    @Override
    public String printImportantInfo() {
        return format(
                "Task Type: %s%n" +
                "Title: %s%n" +
                "Description: %s%n", getTaskType(), getTitle(), getDescription());
    }

    @Override
    public String printComments() {
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

    @Override
    public String printLogHistory() {
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
                printImportantInfo(), getDescription(), printComments(), printLogHistory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskBase taskBase = (TaskBase) o;
        return title.equalsIgnoreCase(taskBase.title);
    }
}
