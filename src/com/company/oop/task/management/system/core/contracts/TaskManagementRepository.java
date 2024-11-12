package com.company.oop.task.management.system.core.contracts;

import com.company.oop.task.management.system.models.tasks.BugImpl;
import com.company.oop.task.management.system.models.tasks.CommentImpl;
import com.company.oop.task.management.system.models.tasks.FeedbackImpl;
import com.company.oop.task.management.system.models.tasks.StoryImpl;
import com.company.oop.task.management.system.models.tasks.enums.*;
import com.company.oop.task.management.system.models.teams.BoardImpl;
import com.company.oop.task.management.system.models.teams.MemberImpl;
import com.company.oop.task.management.system.models.teams.TeamImpl;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.List;

public interface TaskManagementRepository {

    public List<Team> getTeams();

    public void addTeam(Team teamToAdd);

    public Team findTeamByName(String name);

    public void addMemberToTeam(String teamName, Member memberToAdd);

    public void addBoardToTeam(String teamName, Board boardToAdd);

    public List<Member> getTeamMembers(String teamName);

    public List<Board> getTeamBoards(String teamName);

    public TeamImpl createTeam(String name);

    public BoardImpl createBoard(String name);

    public MemberImpl createMember(String name);

    public CommentImpl createComment(String content, String author);

    public StoryImpl createStory(int id, String title, String description, Priority priority, StorySize size,
                                 StoryStatus status, String assignee);

    public BugImpl createBug(int id, String title, String description, List<String> reproducibleSteps,
                             Priority priority, Severity severity, BugStatus bugStatus, Member assignee);

    public FeedbackImpl createFeedback(int id, String title, String description, int rating);
}
