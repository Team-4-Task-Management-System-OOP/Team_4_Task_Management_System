package com.company.oop.task.management.system.models.tasks;

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
        return comments;
    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
        historyLogger(String.format("A comment was added to item with ID: %d.", getId()));
    }

    @Override
    public void historyLogger (String log){
        history.add(log);
    }

    @Override
    public String printInfo(){
        return String.format("Id: %d%n" +
                "Title: %s%n", getId(), getTitle());
    }

    private String printComments() {

        if (getComments().isEmpty()) {
            return "---NO COMMENTS---";
        } else {

            StringBuilder result = new StringBuilder();
            for (Comment comment : comments) {
                result.append(comment).append(System.lineSeparator());
            }
            return result.toString();
        }
    }

    @Override
    public String toString() {
        return String.format("%s" +
                        "Description: %s%n" +
                        "Comments: %s%n" +
                        "History: %s",
                printInfo(), getDescription(), printComments(), getHistory());
    }

}
