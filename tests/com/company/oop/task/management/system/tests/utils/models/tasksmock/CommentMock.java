package com.company.oop.task.management.system.tests.utils.models.tasksmock;

import com.company.oop.task.management.system.models.tasks.CommentImpl;
import com.company.oop.task.management.system.tests.utils.TestUtilities;

import static com.company.oop.task.management.system.models.tasks.CommentImpl.AUTHOR_LEN_MIN;
import static com.company.oop.task.management.system.models.tasks.CommentImpl.CONTENT_LEN_MIN;

public class CommentMock {
    public static final String VALID_CONTENT = TestUtilities.getString(CONTENT_LEN_MIN + 1);
    public static final String VALID_AUTHOR = TestUtilities.getString(AUTHOR_LEN_MIN + 1);

    public static final String INVALID_CONTENT = TestUtilities.getString(CONTENT_LEN_MIN - 1);
    public static final String INVALID_AUTHOR = TestUtilities.getString(AUTHOR_LEN_MIN - 1);

    public static final String NULL_CONTENT = null;
    public static final String NULL_AUTHOR = null;

    public static CommentImpl getValidCommentMock() {
        return new CommentImpl(
                VALID_CONTENT,
                VALID_AUTHOR
        );
    }

    public static CommentImpl getInvalidCommentMock() {
        return new CommentImpl(
                INVALID_CONTENT,
                INVALID_AUTHOR
        );
    }

    public static CommentImpl getInvalidContentCommentMock() {
        return new CommentImpl(
                INVALID_CONTENT,
                VALID_AUTHOR
        );
    }

    public static CommentImpl getInvalidAuthorCommentMock() {
        return new CommentImpl(
                VALID_CONTENT,
                INVALID_AUTHOR
        );
    }

    public static CommentImpl getNullCommentMock() {
        return new CommentImpl(
                NULL_CONTENT,
                NULL_AUTHOR
        );
    }

}

