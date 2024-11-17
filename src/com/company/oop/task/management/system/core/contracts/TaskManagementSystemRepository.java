package com.company.oop.task.management.system.core.contracts;

import com.company.oop.task.management.system.models.tasks.contracts.*;
import com.company.oop.task.management.system.models.tasks.enums.*;
import com.company.oop.task.management.system.models.teams.TeamImpl;
import com.company.oop.task.management.system.models.teams.contracts.Board;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import com.company.oop.task.management.system.models.teams.contracts.Nameable;
import com.company.oop.task.management.system.models.teams.contracts.Team;

import java.util.List;

public interface TaskManagementSystemRepository {


    List<Member> getMembers();

    List<Board> getBoards();

    List<Team> getTeams();

    List<Task> getTasks();

    List<Bug> getBugs();

    List<Feedback> getFeedbacks();

    List<Story> getStories();

    List<Assignable> getAssignedTasks();

    // public void addTeam(Team teamToAdd);

    Team findTeamByName(String name);

    Member findMemberByName(String memberName);

    Board findBoardByName(String boardName);

    void addMemberToTeam(String teamName, Member memberToAdd);

    void addBoardToTeam(String teamName, Board boardToAdd);

    Comment createComment(String content, String author);

    Feedback createFeedback(String title, String description, int rating);

    Story createStory(String title, String description, PriorityType priorityType,
                      StorySize size);

    Bug createBug(String title, String description, List<String> reproducibleSteps,
                  PriorityType priority, BugSeverity bugSeverity);

    Member createMember(String name);

    Team createTeam(String name);

    Board createBoard(String name, Team team);

    <T extends Identifiable> T findTaskById(List<T> elements, int id);

    <T extends Nameable> T findElementByName(List<T> elements, String name);

    Member getLoggedInMember();

    boolean hasLoggedInMember();

    void login(Member member);

    void logout();

}
