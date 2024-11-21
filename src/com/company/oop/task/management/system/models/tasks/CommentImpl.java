package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.models.tasks.contracts.Comment;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validateNotNull;
import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class CommentImpl implements Comment {

    public static final int AUTHOR_LEN_MIN = 4;
    public static final int AUTHOR_LEN_MAX = 15;
    public static final int CONTENT_LEN_MIN = 2;
    public static final int CONTENT_LEN_MAX = 200;

    private static final String AUTHOR_LEN_ERR = format(
            "Author's name must be between %d and %d characters long!",
            AUTHOR_LEN_MIN,
            AUTHOR_LEN_MAX);
    private static final String CONTENT_LEN_ERR = format(
            "Content must be between %d and %d characters long!",
            CONTENT_LEN_MIN,
            CONTENT_LEN_MAX);
    public static final String COMMENT_HEADLINE = "%n---COMMENT---%n";

    private String author;
    private String content;

    public CommentImpl() {
    }

    public CommentImpl(String content, String author) {
        setContent(content);
        setAuthor(author);
    }

    private void setAuthor(String author) {
        validateNotNull(author);
        validateStringLength(author, AUTHOR_LEN_MIN, AUTHOR_LEN_MAX, AUTHOR_LEN_ERR);
        this.author = author;
    }

    private void setContent(String content) {
        validateNotNull(content);
        validateStringLength(content, CONTENT_LEN_MIN, CONTENT_LEN_MAX, CONTENT_LEN_ERR);
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format(COMMENT_HEADLINE +
                "Author: %s%n" +
                "Content: %s%n" +
                "-------------", getAuthor(), getContent());
    }
}
