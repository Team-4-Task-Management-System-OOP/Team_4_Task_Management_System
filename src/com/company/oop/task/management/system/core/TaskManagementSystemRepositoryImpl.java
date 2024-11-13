package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.BugImpl;
import com.company.oop.task.management.system.models.tasks.CommentImpl;
import com.company.oop.task.management.system.models.tasks.FeedbackImpl;
import com.company.oop.task.management.system.models.tasks.StoryImpl;
import com.company.oop.task.management.system.models.tasks.contracts.*;
import com.company.oop.task.management.system.models.tasks.enums.*;
import com.company.oop.task.management.system.models.teams.BoardImpl;
import com.company.oop.task.management.system.models.teams.MemberImpl;
import com.company.oop.task.management.system.models.teams.TeamImpl;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private static final String NO_SUCH_TEAM = "There is no team with name %s!";
    private static final String TEAM_ALREADY_EXISTS = "Team %s already exists. Choose a different name!";
    private static final String MEMBER_ALREADY_EXISTS = "Member %s already exists. Choose a different name!";
    private static final String BOARD_ALREADY_EXISTS = "Board %s already exists in team %s!";

    private int nextId;
    private Member loggedMember;

    private final List<Member> members;
    private final List<Board> boards;
    private final List<Team> teams;

    private final List<Task> tasks;
    private final List<Assignable> assignableTasks;

    private final List<Bug> bugs;
    private final List<Story> stories;
    private final List<Feedback> feedbacks;

    public TaskManagementSystemRepositoryImpl() {
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.bugs = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.assignableTasks = new ArrayList<>();
        nextId = 0;
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

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
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        FeedbackImpl feedback = new FeedbackImpl(++nextId, title, description, rating);
        this.tasks.add(feedback);
        this.feedbacks.add(feedback);
        return feedback;
    }

    @Override
    public Story createStory(String title, String description,
                             PriorityType priorityType, StorySize size,
                             StoryStatus status, Member assignee) {
        StoryImpl story = new StoryImpl(++nextId, title, description,
                priorityType, size, status, assignee);
        this.tasks.add(story);
        this.stories.add(story);
        this.assignableTasks.add(story);
        return story;
    }

    @Override
    public Bug createBug(String title, String description,
                         List<String> reproducibleSteps, PriorityType priority,
                         BugSeverity bugSeverity, BugStatus bugStatus, Member assignee) {
        BugImpl bug = new BugImpl(++nextId, title, description, reproducibleSteps,
                priority, bugSeverity, bugStatus, assignee);
        this.tasks.add(bug);
        this.bugs.add(bug);
        this.assignableTasks.add(bug);
        return null;
    }

    @Override
    public Member createMember(String name) {
        return new MemberImpl(name);
    }

    @Override
    public Team createTeam(String name) {
        return new TeamImpl(name);
    }

    @Override
    public Board createBoard(String name) {
        return new BoardImpl(name);
    }

    @Override
    public boolean hasLoggedInMember() {
        return loggedMember != null;
    }

    @Override
    public void login(Member member) {
        loggedMember = member;
    }

    @Override
    public void logout() {
        loggedMember = null;
    }
}
