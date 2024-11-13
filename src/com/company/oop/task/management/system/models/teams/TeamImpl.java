package com.company.oop.task.management.system.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.ArrayList;
import java.util.List;

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
    private static final String NAME_UNIQUE_MESSAGE = "Member name must be unique!";
    private static final String BOARD_UNIQUE_MESSAGE = "Board name must be unique!";
    private static final String NOT_EXISTING_MEMBER_MESSAGE = "Member doesn't exist!";
    private static final String NOT_EXISTING_BOARD_MESSAGE = "Board doesn't exist!";

    // Fields
    private String name;
    private final List<Member> members;
    private final List<Board> boards;
    private final List<String> history;

    //Constructor
    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    //Setters
    private void setName(String name) {
        validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_LENGTH_ERR);
        // TODO - Consider checking for unique name in the CommandFactory (eventually)
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

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    //Methods
    //ToDo current time might be need - ParsingHelpers.formatTime
    @Override
    public void addActivityHistory(String description) {
        history.add(description);
    }

    @Override
    public void addMember(Member member) {
        if (members.stream().anyMatch(m -> m.getName().equals(member.getName()))) {
            throw new InvalidUserInputException(NAME_UNIQUE_MESSAGE);
        }
        members.add(member);
    }

    @Override
    public void removeMember(Member member) {
        if (members.stream().anyMatch(m -> m.getName().equals(member.getName()))) {
            members.remove(member);
        } else {
            throw new InvalidUserInputException(NOT_EXISTING_MEMBER_MESSAGE);
        }
    }

    @Override
    public void addBoard(Board board) {
        if (boards.stream().anyMatch(b -> b.getName().equals(board.getName()))) {
            throw new InvalidUserInputException(BOARD_UNIQUE_MESSAGE);
        }
        boards.add(board);
    }

    @Override
    public void removeBoard(Board board) {
        if (boards.stream().anyMatch(b -> b.getName().equals(board.getName()))) {
            boards.remove(board);
        }
        throw new InvalidUserInputException(NOT_EXISTING_BOARD_MESSAGE);
    }

    //Print
    @Override
    public String toString() {
        return format("---Team---%n" +
                "%nName: %s" +
                "%n---Members---%n%s" +
                "%n---Boards---%n%s", getName(), members.toString(), boards.toString());
    }

}
