package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.exceptions.ElementNotFoundException;
import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.BugImpl;
import com.company.oop.task.management.system.models.tasks.FeedbackImpl;
import com.company.oop.task.management.system.models.tasks.StoryImpl;
import com.company.oop.task.management.system.models.tasks.contracts.*;
import com.company.oop.task.management.system.models.tasks.enums.BugSeverity;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.StorySize;
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
    public static final String MEMBER_ALREADY_EXISTS = "Member \"s\" already exists. Choose a different name!";
    public static final String NO_LOGGED_IN_MEMBER = "There is no logged in member.";
    public static final String NO_LOGGED_IN_TEAM = "There is no logged in team.";
    public static final String NO_ELEMENT_NAME_FOUND = "No %s with name \"%s\" exists!";
    public static final String NO_TASK_WITH_ID_FOUND = "No task with ID %d";
    public static final String BOARD_ALREADY_EXISTS_IN_REPO = "A board with this name already exists and cannot be created! " +
            "Please, provide a different board name!";
    public static final String TEAM_ALREADY_EXISTS_IN_REPO = "A team with this name already exists and cannot be created! " +
            "Please, provide a different team name!";
    public static final String MEMBER_ALREADY_EXISTS_IN_REPO = "A member with this name already exists and cannot be created! " +
            "Please, provide a different member name!";
    public static final String BUG_ALREADY_EXISTS_IN_REPO = "A task with the same title already exists and cannot be created! " +
            "Please, provide a different task title!";
    public static final String STORY_ALREADY_EXISTS_IN_REPO = "A task with the same title already exists and cannot be created! " +
            "Please, provide a different task title!";
    public static final String FEEDBACK_ALREADY_EXISTS_IN_REPO = "A task with the same title already exists and cannot be created! " +
            "Please, provide a different task title!";

    private int nextId;
    private Member loggedMember;
    private Team loggedTeam;

    private final List<Member> members;
    private final List<Board> boards;
    private final List<Team> teams;

    private final List<Task> tasks;
    private final List<Assignable> assignableTasks;

    private final List<Bug> bugs;
    private final List<Story> stories;
    private final List<Feedback> feedbacks;

    public TaskManagementSystemRepositoryImpl() {
        this.members = new ArrayList<>(); // ALL MEMBERS
        this.boards = new ArrayList<>();
        this.teams = new ArrayList<>(); // ALL TEAMS
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

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    public List<Assignable> getAssignedTasks() {
        return new ArrayList<>(assignableTasks);
    }

//    @Override
//    public void addMemberToTeam(String teamName, Member memberToAdd) {
//        Team team = findTeamByName(teamName);
//        if (team.getMembers().stream().anyMatch(m -> m.getName().equalsIgnoreCase(memberToAdd.getName()))) {
//            throw new InvalidUserInputException(String.format(MEMBER_ALREADY_EXISTS, memberToAdd.getName()));
//        }
//        team.addMember(memberToAdd);
//    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        FeedbackImpl feedback = new FeedbackImpl(++nextId, title, description, rating);
        if (!getTasks().contains(feedback)) {
            this.tasks.add(feedback);
            this.feedbacks.add(feedback);
        } else {
            throw new InvalidUserInputException(FEEDBACK_ALREADY_EXISTS_IN_REPO);
        }
        return feedback;
    }

    @Override
    public Story createStory(String title, String description,
                             PriorityType priorityType, StorySize size) {
        StoryImpl story = new StoryImpl(++nextId, title, description,
                priorityType, size);
        if (!getTasks().contains(story)) {
            this.tasks.add(story);
            this.stories.add(story);
            this.assignableTasks.add(story);
        } else {
            throw new InvalidUserInputException(STORY_ALREADY_EXISTS_IN_REPO);
        }
        return story;
    }

    @Override
    public Bug createBug(String title, String description,
                         List<String> reproducibleSteps, PriorityType priority,
                         BugSeverity bugSeverity) {
        BugImpl bug = new BugImpl(++nextId, title, description, reproducibleSteps,
                priority, bugSeverity);
        if (!getTasks().contains(bug)) {
            this.tasks.add(bug);
            this.bugs.add(bug);
            this.assignableTasks.add(bug);
        } else {
            throw new InvalidUserInputException(BUG_ALREADY_EXISTS_IN_REPO);
        }
        return bug;
    }

    @Override
    public Member createMember(String name) {
        Member member = new MemberImpl(name);
        if (!getMembers().contains(member)) {
            members.add(member);
        } else {
            throw new InvalidUserInputException(MEMBER_ALREADY_EXISTS_IN_REPO);
        }
        return new MemberImpl(name);
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        if(!getTeams().contains(team)){
            teams.add(team);
        }
        else {
            throw new InvalidUserInputException(TEAM_ALREADY_EXISTS_IN_REPO);
        }
        return team;
    }

    @Override
    public Board createBoard(String boardName, Team teamName) {
        Team team = findTeamByName(teamName.getName());
        Board board = new BoardImpl(boardName);
        if (!getBoards().contains(board)){
            boards.add(board);
            team.addBoard(board);
        }
        else {
            throw new InvalidUserInputException(BOARD_ALREADY_EXISTS_IN_REPO);
        }
        return board;
    }

    @Override
    public Member findMemberByName(String memberName) {
        return findElementByName(getMembers(), memberName, "member");
    }

    @Override
    public Board findBoardByName(String boardName) {
        return findElementByName(getBoards(), boardName, "board");
    }

    @Override
    public Team findTeamByName(String teamName) {
        return findElementByName(getTeams(), teamName, "team");
    }

    @Override
    public <T extends Identifiable> T findTaskById(List<T> elements, int id) {
        for (T element : elements) {
            if (element.getId() == id) {
                return element;
            }
        }
        throw new ElementNotFoundException(String.format(NO_TASK_WITH_ID_FOUND, id));
    }

    @Override
    public <T extends Nameable> T findElementByName(List<T> elements, String name, String className) {
        return elements
                .stream()
                .filter(element -> element.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format(NO_ELEMENT_NAME_FOUND, className, name)));
    }

    @Override
    public Member getLoggedInMember() {
        if (loggedMember == null) {
            throw new InvalidUserInputException(NO_LOGGED_IN_MEMBER);
        }
        return loggedMember;
    }
    @Override
    public Team getLoggedInTeam() {
        if (loggedTeam == null) {
            throw new InvalidUserInputException(NO_LOGGED_IN_TEAM);
        }
        return loggedTeam;
    }

    @Override
    public boolean hasLoggedInMember() {
        return loggedMember != null;
    }

    @Override
    public void loginMember(Member member) {
        loggedMember = member;
    }

    @Override
    public void loginTeam(Team team) {
        loggedTeam = team;
    }

    @Override
    public void logoutMember() {
        loggedMember = null;
    }
    @Override
    public void logoutTeam() {
        loggedTeam = null;
    }
}
