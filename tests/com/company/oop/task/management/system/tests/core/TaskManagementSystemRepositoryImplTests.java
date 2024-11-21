package com.company.oop.task.management.system.tests.core;
import com.company.oop.task.management.system.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.models.tasks.contracts.Story;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.tasks.enums.BugSeverity;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.StorySize;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskManagementSystemRepositoryImplTests {

    @Test
    public void test_create_and_add_new_member() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        Member newMember = repository.createMember("JohnDoe");
        assertTrue(repository.getMembers().contains(newMember));
    }

    @Test
    public void test_create_member_with_existing_name() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        repository.createMember("JohnDoe");
        Exception exception = assertThrows(InvalidUserInputException.class, () -> {
            repository.createMember("JohnDoe");
        });
        assertEquals("A member with this name already exists and cannot be created! Please, provide a different member name!", exception.getMessage());
    }

    @Test
    public void test_create_and_add_new_team() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        Team newTeam = repository.createTeam("DevTeam");
        assertTrue(repository.getTeams().contains(newTeam));
    }

    @Test
    public void test_create_board_with_existing_name_throws_exception() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        Team team = repository.createTeam("TeamA");
        repository.createBoard("Board1", team);

        InvalidUserInputException exception = assertThrows(
                InvalidUserInputException.class,
                () -> repository.createBoard("Board1", team)
        );

        assertEquals("A board with this name already exists and cannot be created! Please, provide a different board name!", exception.getMessage());
    }

    @Test
    public void test_create_team_with_existing_name_throws_exception() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        String teamName = "ExistingTeam";
        repository.createTeam(teamName);
        Exception exception = assertThrows(InvalidUserInputException.class, () -> {
            repository.createTeam(teamName);
        });
        assertEquals(TaskManagementSystemRepositoryImpl.TEAM_ALREADY_EXISTS_IN_REPO, exception.getMessage());
    }

    @Test
    public void test_create_and_add_new_bug_story_feedback() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();

        Bug newBug = repository.createBug("BugTitle12345", "BugDescription", List.of("Step1"), PriorityType.HIGH, BugSeverity.CRITICAL);
        assertTrue(repository.getBugs().contains(newBug));
        assertTrue(repository.getTasks().contains(newBug));
        assertTrue(repository.getAssignedTasks().contains(newBug));

        Story newStory = repository.createStory("StoryTitle", "StoryDescription", PriorityType.MEDIUM, StorySize.LARGE);
        assertTrue(repository.getStories().contains(newStory));
        assertTrue(repository.getTasks().contains(newStory));
        assertTrue(repository.getAssignedTasks().contains(newStory));

        Feedback newFeedback = repository.createFeedback("FeedbackTitle", "FeedbackDescription", 5);
        assertTrue(repository.getFeedbacks().contains(newFeedback));
        assertTrue(repository.getTasks().contains(newFeedback));
    }

    @Test
    public void test_create_and_add_new_board_to_team() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        Team newTeam = repository.createTeam("AlphaTeam");
        Board newBoard = repository.createBoard("Project", newTeam);
        assertTrue(repository.getBoards().contains(newBoard));
        assertTrue(newTeam.getBoards().contains(newBoard));
    }

    @Test
    public void test_find_task_by_nonexistent_id_throws_exception() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        int nonexistentId = 999;
        assertThrows(ElementNotFoundException.class, () -> {
            repository.findTaskById(repository.getTasks(), nonexistentId);
        });
    }

    @Test
    public void test_next_id_increments_with_task_creation() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();

        int initialId = repository.createFeedback("1234567890001", "Description1", 5).getId();
        int nextId = repository.createStory("123456789000000", "Description2", PriorityType.HIGH, StorySize.LARGE).getId();
        int subsequentId = repository.createBug("123456789000", "Description3", List.of("Step1"), PriorityType.MEDIUM, BugSeverity.CRITICAL).getId();

        assertEquals(initialId + 1, nextId);
        assertEquals(nextId + 1, subsequentId);
    }

 //   @Test
//    public void test_add_member_to_team_throws_exception_when_member_already_exists() {
//        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
//        Team team = repository.createTeam("AlphaTeam");
//        Member member = repository.createMember("JohnDoe");
//        team.addMember(member);
//
//        InvalidUserInputException exception = assertThrows(
//                InvalidUserInputException.class,
//                () -> repository.addMemberToTeam("AlphaTeam", member)
//        );
//
//        assertEquals("Member \"s\" already exists. Choose a different name!", exception.getMessage());
//    }

    @Test
    public void test_find_element_by_nonexistent_name_throws_exception() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        String nonExistentName = "NonExistentElement";
        assertThrows(ElementNotFoundException.class, () -> {
            repository.findElementByName(repository.getMembers(), nonExistentName, "member");
        });
    }

    @Test
    public void test_create_and_add_new_bug() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        List<String> steps = new ArrayList<>();
        steps.add("Step 1");
        Bug newBug = repository.createBug("BugTitle12345", "BugDescription", steps, PriorityType.HIGH, BugSeverity.CRITICAL);
        assertTrue(repository.getTasks().contains(newBug));
        assertTrue(repository.getAssignedTasks().contains(newBug));
    }

//    @Test
//    public void test_add_existing_member_to_team_throws_exception() {
//        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
//        Team team = repository.createTeam("AlphaTeam");
//        Member member = repository.createMember("JohnDoe");
//        team.addMember(member);
//
//        InvalidUserInputException exception = assertThrows(
//                InvalidUserInputException.class,
//                () -> repository.addMemberToTeam("AlphaTeam", member)
//        );
//
//        assertEquals("Member \"s\" already exists. Choose a different name!", exception.getMessage());
//    }

    @Test
    public void test_login_and_logout_member_and_team() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();
        Member member = repository.createMember("JohnDoe");
        Team team = repository.createTeam("DevTeam");

        repository.loginMember(member);
        assertTrue(repository.hasLoggedInMember());
        assertEquals(member, repository.getLoggedInMember());

        repository.logoutMember();
        assertFalse(repository.hasLoggedInMember());

        repository.loginTeam(team);
        assertEquals(team, repository.getLoggedInTeam());

        repository.logoutTeam();
        try {
            repository.getLoggedInTeam();
            fail("Expected InvalidUserInputException to be thrown");
        } catch (InvalidUserInputException e) {
            assertEquals("There is no logged in team.", e.getMessage());
        }
    }

    @Test
    public void test_retrieve_logged_in_member_or_team_when_none_logged_in() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();

        Exception memberException = assertThrows(InvalidUserInputException.class, repository::getLoggedInMember);
        assertEquals("There is no logged in member.", memberException.getMessage());

        Exception teamException = assertThrows(InvalidUserInputException.class, repository::getLoggedInTeam);
        assertEquals("There is no logged in team.", teamException.getMessage());
    }

    @Test
    public void test_retrieve_lists_without_errors() {
        TaskManagementSystemRepositoryImpl repository = new TaskManagementSystemRepositoryImpl();

        Member member = repository.createMember("Alice");
        Team team = repository.createTeam("Developers");
        Board board = repository.createBoard("Project", team);
        Bug bug = repository.createBug("1234567890", "Description1", List.of("Step1"), PriorityType.HIGH, BugSeverity.CRITICAL);
        Story story = repository.createStory("Story1234567", "Description2", PriorityType.MEDIUM, StorySize.LARGE);
        Feedback feedback = repository.createFeedback("Feedback12345", "Description3", 5);

        List<Member> members = repository.getMembers();
        List<Team> teams = repository.getTeams();
        List<Board> boards = repository.getBoards();
        List<Task> tasks = repository.getTasks();

        assertTrue(members.contains(member));
        assertTrue(teams.contains(team));
        assertTrue(boards.contains(board));
        assertTrue(tasks.contains(bug));
        assertTrue(tasks.contains(story));
        assertTrue(tasks.contains(feedback));
    }


}