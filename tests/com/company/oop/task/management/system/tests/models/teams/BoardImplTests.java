package com.company.oop.task.management.system.tests.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.BugImpl;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.teams.BoardImpl;
import com.company.oop.task.management.system.tests.utils.models.tasksmock.BugMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.task.management.system.models.teams.BoardImpl.CANNOT_ADD_AN_EMPTY_TASK_BOARD;
import static org.junit.jupiter.api.Assertions.*;

public class BoardImplTests {
    private BoardImpl board;
    //ToDo
    @BeforeEach
    void setUp() {
        board = new BoardImpl("DevBoard");
    }

    @Test
    void testBoardInitialization() {
        assertNotNull(board);
        assertEquals("DevBoard", board.getName());
        assertTrue(board.getTasks().isEmpty());
        assertTrue(board.getHistory().isEmpty());
    }

    @Test
    void testAddTask_ValidTask() {
        BugImpl task = BugMock.getValidBugMock();
        board.addTask(task);

        List<Task> tasks = board.getTasks();
        List<String> history = board.getHistory();

        assertEquals(1, tasks.size());
        assertEquals(task.getTitle(), tasks.get(0).getTitle());
        assertEquals(1, history.size());
        assertEquals(task.getTitle(), tasks.get(0).getTitle());
    }

    @Test
    void testAddTask_NullTask_ShouldThrowException() {
        InvalidUserInputException exception = assertThrows(InvalidUserInputException.class, () -> board.addTask(null));
        assertEquals(CANNOT_ADD_AN_EMPTY_TASK_BOARD, exception.getMessage());
    }

    @Test
    void testGetTasks_WhenNoTasksAdded_ShouldReturnEmptyList() {
        assertTrue(board.getTasks().isEmpty());
    }

    @Test
    void testGetActivityHistory_WhenNoActivity_ShouldReturnEmptyList() {
        assertTrue(board.getHistory().isEmpty());
    }

    @Test
    public void test_remove_null_task_throws_exception() {
        BoardImpl board = new BoardImpl("ValidName");
        Exception exception = assertThrows(InvalidUserInputException.class, () -> {
            board.removeTask((Task) null);
        });
        assertEquals("Cannot remove an empty task.", exception.getMessage());
    }

    @Test
    public void test_add_null_task_throws_exception() {
        BoardImpl board = new BoardImpl("ValidName");
        Exception exception = assertThrows(InvalidUserInputException.class, () -> {
            board.addTask((Task) null);
        });
        assertEquals("Cannot add an empty task to the board!", exception.getMessage());
    }

}
