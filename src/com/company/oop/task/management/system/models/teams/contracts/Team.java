package com.company.oop.task.management.system.models.teams.contracts;

import com.company.oop.task.management.system.models.teams.MemberImpl;

import java.util.List;

public interface Team {

    String getName();

    List<Member> getMembers();

    List<Board> getBoards();

    void addMember(Member member);

    void removeMember(Member member);

    void addBoard(Board board);

    void removeBoard(Board board);
}
