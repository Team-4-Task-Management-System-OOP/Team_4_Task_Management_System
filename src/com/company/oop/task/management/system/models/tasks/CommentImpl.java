package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.models.tasks.contracts.Comment;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class CommentImpl implements Comment {

    //Constants
    private static final int AUTHOR_LEN_MIN = 4;
    private static final int AUTHOR_LEN_MAX = 15;
    private static final int CONTENT_LEN_MIN = 2;
    private static final int CONTENT_LEN_MAX = 200;

    private static final String AUTHOR_LEN_ERR = format(
            "Author's name must be between %d and %d characters long!",
            AUTHOR_LEN_MIN,
            AUTHOR_LEN_MAX);
    private static final String CONTENT_LEN_ERR = format(
            "Content must be between %d and %d characters long!",
            CONTENT_LEN_MIN,
            CONTENT_LEN_MAX);
    private static final String COMMENT_HEADLINE = "%n---COMMENT---%n";

    //Fields
    private String author;
    private String content;

    //Constructor
    public CommentImpl(String content, String author) {
        setContent(content);
        setAuthor(author);
    }

    //Setters
    private void setAuthor(String author) {
        validateStringLength(author, AUTHOR_LEN_MIN, AUTHOR_LEN_MAX, AUTHOR_LEN_ERR);
        this.author = author;
    }

    private void setContent(String content) {
        validateStringLength(content, CONTENT_LEN_MIN, CONTENT_LEN_MAX, CONTENT_LEN_ERR);
        this.content = content;
    }

    //Getters
    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    //Print
    @Override
    public String toString() {
        return String.format(COMMENT_HEADLINE +
                "Author: %s%n" +
                "Content: %s%n" +
                "-------------", getAuthor(), getContent());
    }
}
