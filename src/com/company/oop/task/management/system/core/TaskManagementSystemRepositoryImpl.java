package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;
import com.company.oop.task.management.system.models.tasks.BugImpl;
import com.company.oop.task.management.system.models.tasks.CommentImpl;
import com.company.oop.task.management.system.models.tasks.FeedbackImpl;
import com.company.oop.task.management.system.models.tasks.StoryImpl;
import com.company.oop.task.management.system.models.tasks.contracts.*;
import com.company.oop.task.management.system.models.tasks.enums.*;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {

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
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        FeedbackImpl feedback = new FeedbackImpl(++nextId, title, description, rating);
        this.tasks.add(feedback);
        return feedback;
    }

    @Override
    public Story createStory(String title, String description,
                             PriorityType priorityType, StorySize size,
                             StoryStatus status, Member assignee) {
        StoryImpl story = new StoryImpl(++nextId, title, description,
                priorityType, size, status, assignee);
        this.tasks.add(story);
        return story;
    }

    @Override
    public Bug createBug(String title, String description,
                         List<String> reproducibleSteps, PriorityType priority,
                         BugSeverity bugSeverity, BugStatus bugStatus, Member assignee) {
        BugImpl bug = new BugImpl(++nextId, title, description, reproducibleSteps,
                priority, bugSeverity, bugStatus, assignee);
        this.tasks.add(bug);
        return null;
    }

    @Override
    public Member createMember(String name) {
        return null;
    }

    @Override
    public Team createTeam(String name) {
        return null;
    }

    @Override
    public Board createBoard(String name) {
        return null;
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
