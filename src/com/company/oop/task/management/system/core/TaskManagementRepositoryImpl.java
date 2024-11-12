package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.core.contracts.TaskManagementRepository;
import com.company.oop.task.management.system.models.tasks.*;
import com.company.oop.task.management.system.models.tasks.enums.*;
import com.company.oop.task.management.system.models.teams.*;
import com.company.oop.task.management.system.models.teams.contracts.*;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    private static final String NO_SUCH_TEAM = "There is no team with name %s!";
    private static final String TEAM_ALREADY_EXISTS = "Team %s already exists. Choose a different name!";
    private static final String MEMBER_ALREADY_EXISTS = "Member %s already exists. Choose a different name!";
    public static final String BOARD_ALREADY_EXISTS = "Board %s already exists in team %s!";

    private final List<Team> teams;

    public TaskManagementRepositoryImpl(List<Team> teams) {
        this.teams = new ArrayList<>();
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public void addTeam(Team teamToAdd) {
        if (teams.stream().anyMatch(t -> t.getName().equalsIgnoreCase(teamToAdd.getName()))) {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXISTS, teamToAdd.getName()));
        }
        this.teams.add(teamToAdd);
    }

    @Override
    public Team findTeamByName(String name) {
        return teams.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_TEAM, name)));
    }

    @Override
    public void addMemberToTeam(String teamName, Member memberToAdd) {
        Team team = findTeamByName(teamName);
        if (team.getMembers().stream().anyMatch(m -> m.getName().equalsIgnoreCase(memberToAdd.getName()))) {
            throw new IllegalArgumentException(String.format(MEMBER_ALREADY_EXISTS, memberToAdd.getName()));
        }
        team.addMember(memberToAdd);
    }

    @Override
    public void addBoardToTeam(String teamName, Board boardToAdd) {
        Team team = findTeamByName(teamName);
        if (team.getBoards().stream().anyMatch(b -> b.getName().equalsIgnoreCase(boardToAdd.getName()))) {
            throw new IllegalArgumentException(String.format(BOARD_ALREADY_EXISTS, boardToAdd.getName(), teamName));
        }
        team.addBoard(boardToAdd);
    }

    @Override
    public List<Member> getTeamMembers(String teamName) {
        return findTeamByName(teamName).getMembers();
    }

    @Override
    public List<Board> getTeamBoards(String teamName) {
        return findTeamByName(teamName).getBoards();
    }

    @Override
    public TeamImpl createTeam(String name) {
        return new TeamImpl(name);
    }

    @Override
    public BoardImpl createBoard(String name) {
        return new BoardImpl(name);
    }

    @Override
    public MemberImpl createMember(String name) {
        return new MemberImpl(name);
    }

    @Override
    public CommentImpl createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public StoryImpl createStory(int id, String title, String description, Priority priority, StorySize size,
                                 StoryStatus status, String assignee){
        return new StoryImpl(id, title, description, priority, size, status, assignee);
    }

    @Override
    public BugImpl createBug(int id, String title, String description, List<String> reproducibleSteps,
                                 Priority priority, Severity severity, BugStatus bugStatus, Member assignee){
        return new BugImpl(id, title, description, reproducibleSteps, priority, severity, bugStatus, assignee);
    }

    @Override
    public FeedbackImpl createFeedback(int id, String title, String description, int rating){
        return new FeedbackImpl(id, title, description, rating);
    }
}
