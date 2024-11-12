package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.BugStatus;
import com.company.oop.task.management.system.models.tasks.enums.Priority;
import com.company.oop.task.management.system.models.tasks.enums.Severity;

import java.util.List;

public interface Bug extends Task {

    List<String> getReproducibleSteps();

    Priority getPriority();

    void changePriority(Priority priority);

    Severity getSeverityType();

    BugStatus getBugStatus();

    void changeBugStatus(BugStatus bugstatus);

}
