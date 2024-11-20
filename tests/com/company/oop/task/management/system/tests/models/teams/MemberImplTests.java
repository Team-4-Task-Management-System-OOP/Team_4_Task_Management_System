package com.company.oop.task.management.system.tests.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.StoryImpl;
import com.company.oop.task.management.system.models.tasks.contracts.Assignable;
import com.company.oop.task.management.system.models.teams.MemberImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.oop.task.management.system.tests.utils.models.tasksmock.BugMock.getBugMock;
import static com.company.oop.task.management.system.tests.utils.models.teamsmock.MemberMock.*;

public class MemberImplTests {
    public static final String MEMBER_NAME_INITIALIZED_CORRECTLY = "The member name should be initialized correctly.";
    public static final String TASK_ADDED_TO_THE_LIST = "Task should be added to the list!";
    public static final String ONE_TASK = "The list should contain exactly one task!";

    private MemberImpl member;
    private Assignable task = getBugMock();

    @BeforeEach
    public void setUpMemberImplConstructor(){
        member = getMockMember();
    }

    @Test
    public void constructor_Should_InitializeMemberName_When_ArgumentsAreValid() {
        Assertions.assertEquals(VALID_MEMBER_NAME, member.getName(), MEMBER_NAME_INITIALIZED_CORRECTLY);
    }

    @Test
    public void constructor_Should_ThrowException_When_MemberNameIsShorterThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> new MemberImpl(INVALID_MEMBER_NAME));
    }

    @Test
    public void addTask_Should_AddTaskToList() {

        member.addTask(task);

        Assertions.assertTrue(member.getTasks().contains(task),
                TASK_ADDED_TO_THE_LIST);

        Assertions.assertEquals(1, member.getTasks().size(),
                ONE_TASK);
    }






}
