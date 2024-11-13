package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
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
import com.company.oop.task.management.system.models.teams.contracts.Nameable;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private static final String NO_SUCH_TEAM = "There is no team with name %s!";
    private static final String TEAM_ALREADY_EXISTS = "Team %s already exists. Choose a different name!";
    private static final String MEMBER_ALREADY_EXISTS = "Member %s already exists. Choose a different name!";
    private static final String BOARD_ALREADY_EXISTS = "Board %s already exists in team %s!";

    private int nextId;
    //Todo security checks with login - how to be implemented? - I think I have done it - Ivan
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
    public Feedback createFeedback(String title, String description, int rating, FeedbackStatus feedbackStatus) {
        FeedbackImpl feedback = new FeedbackImpl(++nextId, title, description, rating, feedbackStatus);
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
        return bug;
    }

    @Override
    public Member createMember(String name) {
        Member member = new MemberImpl(name);

        if(!getMembers().contains(member)){
            getMembers().add(member);
        }
        else {
            throw new InvalidUserInputException("A member with this name already exists and cannot be created! " +
                    "Please, provide a different member name!");
        }
        return new MemberImpl(name);
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);

        if(!getTeams().contains(team)){
            getTeams().add(team);
        }
        else {
            throw new InvalidUserInputException("A team with this name already exists and cannot be created!" +
                    "Please, provide a different team name!");
        }
        return team;
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        if (!getBoards().contains(board)){
            getBoards().add(board);
        }
        else {
            throw new InvalidUserInputException("A board with this name already exists and cannot be created!" +
                    "Please, provide a different board name!");
        }
        return board;
    }

    @Override
    public Member findMemberByName(String memberName) {
        return findElementByName(getMembers(), memberName);
    }

    @Override
    public Board findBoardByName(String boardName) {
        return findElementByName(getBoards(), boardName);
    }

    @Override
    public Team findTeamByName(String teamName) {
        return findElementByName(getTeams(), teamName);
    }

    @Override
    public <T extends Identifiable> T findTaskById(List<T> elements, int id) {
        for (T element : elements) {
            if (element.getId() == id) {
                return element;
            }
        }
        throw new ElementNotFoundException(String.format("No task with ID %d", id));
    }

    @Override
    public <T extends Nameable> T findElementByName(List<T> elements, String name) {
        return elements
                .stream()
                .filter(element -> element.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("No %s with name %s exists!",elements.getClass().getSimpleName(), name)));
    }

    @Override
    public Member getLoggedInMember() {
        if (loggedMember == null) {
            throw new IllegalArgumentException("There is no logged in member.");
        }
        return loggedMember;
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
