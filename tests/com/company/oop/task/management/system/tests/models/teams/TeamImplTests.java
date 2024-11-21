package com.company.oop.task.management.system.tests.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.teams.BoardImpl;
import com.company.oop.task.management.system.models.teams.MemberImpl;
import com.company.oop.task.management.system.models.teams.TeamImpl;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TeamImplTests {

    @Test
    public void test_create_team_with_valid_name() {
        TeamImpl team = new TeamImpl("ValidName");
        assertEquals("ValidName", team.getName());
        assertTrue(team.getMembers().isEmpty());
        assertTrue(team.getBoards().isEmpty());
        assertTrue(team.getActivityHistory().isEmpty());
    }

    @Test
    public void test_initialize_team_with_valid_name() {
        TeamImpl team = new TeamImpl("ValidName");
        assertNotNull(team.getMembers());
        assertNotNull(team.getBoards());
        assertNotNull(team.getActivityHistory());
        assertEquals(0, team.getMembers().size());
        assertEquals(0, team.getBoards().size());
        assertEquals(0, team.getActivityHistory().size());
    }

    @Test
    public void test_create_team_with_null_name() {
        Exception exception = assertThrows(InvalidUserInputException.class, () -> {
            new TeamImpl(null);
        });
        assertEquals("Value cannot be empty!", exception.getMessage());
    }

    @Test
    public void test_initialize_team_with_null_name_throws_exception() {
        assertThrows(InvalidUserInputException.class, () -> {
            new TeamImpl(null);
        });
    }

    @Test
    public void remove_existing_member_updates_list_and_history() {
        TeamImpl team = new TeamImpl("ValidName");
        Member member = new MemberImpl("JohnDoe");

        team.addMember(member);
        team.removeMember(member);

        assertFalse(team.getMembers().contains(member));
        assertTrue(team.getActivityHistory()
                .stream()
                .anyMatch(history -> history
                        .contains("Member \"JohnDoe\" was successfully removed from team ValidName.")));
    }

    @Test
    public void add_valid_board_updates_list_and_history() {
        TeamImpl team = new TeamImpl("ValidName");
        Board board = new BoardImpl("Project");

        team.addBoard(board);

        assertTrue(team.getBoards().contains(board));
        assertTrue(team.getActivityHistory().stream()
                .anyMatch(history -> history.contains("Board \"Project\" was successfully added to team ValidName.")));
    }
}
