package com.company.oop.task.management.system.tests.utils.models.tasksmock;

import com.company.oop.task.management.system.models.tasks.CommentImpl;
import com.company.oop.task.management.system.tests.utils.TestUtilities;

import static com.company.oop.task.management.system.models.tasks.CommentImpl.AUTHOR_LEN_MIN;
import static com.company.oop.task.management.system.models.tasks.CommentImpl.CONTENT_LEN_MIN;

public class CommentMock {
    public static final String VALID_CONTENT = TestUtilities.getString(CONTENT_LEN_MIN + 1);
    public static final String VALID_AUTHOR = TestUtilities.getString(AUTHOR_LEN_MIN + 1);
    public static final String INVALID_CONTENT = TestUtilities.getString(CONTENT_LEN_MIN - 1);

    public static CommentImpl getCommentMock() {
        return new CommentImpl(
                VALID_CONTENT,
                VALID_AUTHOR);
    }
}
