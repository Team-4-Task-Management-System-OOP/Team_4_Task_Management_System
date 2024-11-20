package com.company.oop.task.management.system.tests.models.tasks;

import com.company.oop.task.management.system.models.tasks.FeedbackImpl;
import org.junit.jupiter.api.BeforeEach;

import static com.company.oop.task.management.system.tests.utils.models.tasksmock.FeedbackMock.getFeedbackMock;

public class FeedbackImplTests {

    private FeedbackImpl feedback;

    @BeforeEach
    public void setUpFeedbackImplConstructor(){
        feedback = getFeedbackMock();
    }

}
