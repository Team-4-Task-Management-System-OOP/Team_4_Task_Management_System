package com.company.oop.task.management.system.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.utils.ParsingHelpers.formatTime;
import static com.company.oop.task.management.system.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class TeamImpl implements Team {

    // Constants
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_LENGTH_ERR = format(
            "Team's name must be between %d and %d!",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);
    private static final String MEMBER_ALREADY_EXISTS = "Member with name ''%s'' is already added to team %s!" +
            "Please, provide a different member to add to the team.";
    private static final String BOARD_ALREADY_EXISTS = "Board with name ''%s'' is added to team %s! " +
            "Please, provide a different board to add to the team.";
    private static final String NOT_EXISTING_MEMBER_MESSAGE = "Member with name ''%s''" +
            " doesn't exist in team %s!";
    private static final String NOT_EXISTING_BOARD_MESSAGE = "Board with name ''%s''" +
            " doesn't exist in team %s!";
    private static final String NO_MEMBERS = "---NO MEMBERS TO DISPLAY---%nAdd a member first!";
    private static final String NO_BOARDS = "---NO BOARDS TO DISPLAY---%nAdd a board first!";
    private static final String NO_HISTORY = "---NO TEAM HISTORY TO DISPLAY---%nDo some activities first!";
    private static final String BOARD_REMOVED = "Board %s was successfully removed from team %s.";
    private static final String BOARD_ADDED = "Board %s was successfully added to team %s.";
    private static final String MEMBER_REMOVED = "Member %s was successfully removed from team %s.";
    private static final String MEMBER_ADDED = "Member %s was successfully added to team %s.";
    public static final String CANNOT_ADD_AN_EMPTY_MEMBER = "Cannot add an empty member!";
    public static final String CANNOT_REMOVE_AN_EMPTY_MEMBER = "Cannot remove an empty member!";
    public static final String CANNOT_ADD_AN_EMPTY_BOARD = "Cannot add an empty board!";
    public static final String CANNOT_REMOVE_AN_EMPTY_BOARD = "Cannot remove an empty board!";

    //Fields
    private String name;
    private final List<Member> members;
    private final List<Board> boards;
    private final List<String> activityHistory;

    //Constructor
    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    //Setters
    private void setName(String name) {
        validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_LENGTH_ERR);
        this.name = name;
    }

    //Getters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    //Methods
    @Override
    public void addActivityHistory(String history) {
        activityHistory.add(format("[%s] - %s", formatTime(LocalDateTime.now()), history));
    }

    @Override
    public void addMember(Member member) {
        if (member == null) {
            throw new InvalidUserInputException(CANNOT_ADD_AN_EMPTY_MEMBER);
        }
        if (members.stream().anyMatch(m -> m.getName().equalsIgnoreCase(member.getName()))) {
            throw new InvalidUserInputException(format(MEMBER_ALREADY_EXISTS, member.getName(), getName()));
        }
        members.add(member);
        addActivityHistory(format(MEMBER_ADDED, member.getName(), getName()));
    }

    @Override
    public void removeMember(Member member) {
        if (member == null) {
            throw new InvalidUserInputException(CANNOT_REMOVE_AN_EMPTY_MEMBER);
        }
        if (members.stream().anyMatch(m -> m.getName().equalsIgnoreCase(member.getName()))) {
            members.remove(member);
            addActivityHistory(format(MEMBER_REMOVED, member.getName(), getName()));
        } else {
            throw new InvalidUserInputException(format(NOT_EXISTING_MEMBER_MESSAGE, member.getName(), getName()));
        }
    }

    @Override
    public void addBoard(Board board) {
        if (board == null) {
            throw new InvalidUserInputException(CANNOT_ADD_AN_EMPTY_BOARD);
        }
        if (boards.stream().anyMatch(b -> b.getName().equalsIgnoreCase(board.getName()))) {
            throw new InvalidUserInputException(format(BOARD_ALREADY_EXISTS, board.getName(), getName()));
        }
        boards.add(board);
        addActivityHistory(format(BOARD_ADDED, board.getName(), getName()));
    }

    @Override
    public void removeBoard(Board board) {
        if (board == null) {
            throw new InvalidUserInputException(CANNOT_REMOVE_AN_EMPTY_BOARD);
        }
        if (boards.stream().anyMatch(b -> b.getName().equalsIgnoreCase(board.getName()))) {
            boards.remove(board);
            addActivityHistory(format(BOARD_REMOVED, board.getName(), getName()));
        }
        else {
            throw new InvalidUserInputException(format(NOT_EXISTING_BOARD_MESSAGE, board.getName(), getName()));
        }
    }

    //Print
    @Override
    public String printHistory() {
        if (getActivityHistory().isEmpty() || getActivityHistory() == null) {
            return NO_HISTORY;
        }
        StringBuilder printHistory = new StringBuilder();
        for (String history : activityHistory) {
            printHistory.append(history).append(System.lineSeparator());
        }
        return printHistory.toString();
    }

    @Override
    public String printMembers() {
        if (getMembers().isEmpty() || getMembers() == null) {
            return NO_MEMBERS;
        } else {
            StringBuilder printMembers = new StringBuilder();
            for (Member member : members) {
                printMembers.append(member.getName()).append(System.lineSeparator());
            }
            return printMembers.toString();
        }

    }

    @Override
    public String printBoards() {
        if (getBoards().isEmpty() || getBoards() == null) {
            return NO_BOARDS;
        } else {
            StringBuilder printBoards = new StringBuilder();
            for (Board board : boards) {
                printBoards.append(board.getName()).append(System.lineSeparator());
            }
            return printBoards.toString();
        }
    }

    @Override
    public String toString() {
        return format("---Team---%n" +
                "%nTeam Name: %s" +
                "%n---Team Members---%n%s" +
                "%n---Team Boards---%n%s" +
                "%n---Team History---%n%s", getName(), printMembers(), printBoards(), printHistory());
    }

    //Equals Override in order to make contains method work properly in repo
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamImpl team = (TeamImpl) o;
        return name.equalsIgnoreCase(team.name);
    }
}
