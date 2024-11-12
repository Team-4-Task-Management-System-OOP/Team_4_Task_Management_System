package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.BugStatus;
import com.company.oop.task.management.system.models.tasks.enums.BugSeverity;
import com.company.oop.task.management.system.models.teams.contracts.Assignable;

import java.util.List;

public interface Bug extends Task, Assignable {

    List<String> getReproducibleSteps();

    BugSeverity getSeverityType();

    BugStatus getBugStatus();

    void changeBugStatus(BugStatus bugstatus);

    void changeSeverity(BugSeverity bugSeverity);

}
