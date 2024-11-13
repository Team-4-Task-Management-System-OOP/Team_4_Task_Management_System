package com.company.oop.task.management.system.models.teams.contracts;

import java.util.List;

public interface Team extends Nameable {

    List<Member> getMembers();

    List<Board> getBoards();

    List<String> getHistory();

    void addActivityHistory(String description);

    void addMember(Member member);

    void removeMember(Member member);

    void addBoard(Board board);

    void removeBoard(Board board);
}
