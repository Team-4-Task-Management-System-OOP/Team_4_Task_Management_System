package com.company.oop.task.management.system.models.teams.contracts;

import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.teams.MemberImpl;

public interface Assignable {

    Member defaultMember = new MemberImpl("unassigned");

    Member getAssignee();

    PriorityType getPriority();

    void changePriority(PriorityType priority);

    void setAssignee(Member assignee);

}
