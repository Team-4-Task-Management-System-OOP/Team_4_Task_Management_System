package com.company.oop.task.management.system.models.teams.contracts;

import com.company.oop.task.management.system.models.contracts.Printable;

import java.util.List;

public interface Team extends Nameable, Printable {

    List<Member> getMembers();

    List<Board> getBoards();

    List<String> getActivityHistory();

    void addActivityHistory(String history);

    void addMember(Member member);

    void removeMember(Member member);

    void addBoard(Board board);

    void removeBoard(Board board);

    String printHistory();

    String printBoards();

    String printMembers();
}
