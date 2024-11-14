package com.company.oop.task.management.system.commands.contracts;

import java.util.List;

public interface Command {
    public String execute(List<String> parameters);
}
