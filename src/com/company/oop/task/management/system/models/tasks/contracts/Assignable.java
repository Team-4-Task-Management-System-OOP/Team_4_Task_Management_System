package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.teams.MemberImpl;
import com.company.oop.task.management.system.models.teams.contracts.Member;

public interface Assignable extends Task {

    Member DEFAULT_ASSIGNEE = new MemberImpl("Unassigned");

    Member getAssignee();

    void assignMember (Member member);

    void unassignMember();

    PriorityType getPriority();

    void changePriority(PriorityType priority);

}
