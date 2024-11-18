package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.commands.contracts.Command;
import com.company.oop.task.management.system.commands.utils.UserCommandsGuide;
import com.company.oop.task.management.system.core.contracts.CommandFactory;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemEngine;
import com.company.oop.task.management.system.core.contracts.TaskManagementSystemRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManagementSystemEngineImpl implements TaskManagementSystemEngine {

    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";
    private static final String MAIN_SPLIT_SYMBOL = " ";
    private static final String COMPOSITE_PARAMETER_OPEN_SYMBOL = "\"";
    private static final String COMPOSITE_PARAMETER_CLOSE_SYMBOL = "\"";
    private static final String REPORT_SEPARATOR = "####################";

    private final CommandFactory commandFactory;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public TaskManagementSystemEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
    }

    @Override
    public void start() {
       System.out.println(UserCommandsGuide.ENGINE_START_MESSAGE); //ToDo
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    print(EMPTY_COMMAND_ERROR);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    print(ex.getMessage());
                } else {
                    print(ex.toString());
                }
            }
        }
    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        List<String> parameters = extractParameters(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, taskManagementSystemRepository);
        String executionResult = command.execute(parameters);
        print(executionResult);
    }

    private String extractCommandName(String inputLine) {
        return inputLine.split(MAIN_SPLIT_SYMBOL)[0];
    }

    private List<String> extractParameters(String fullCommand) {
        int indexOfFirstSeparator = fullCommand.indexOf(MAIN_SPLIT_SYMBOL);
        List<String> parameterParts = new ArrayList<>(Arrays.asList(
                fullCommand
                .substring(indexOfFirstSeparator + 1)
                .split(MAIN_SPLIT_SYMBOL)));
        List<String> result = new ArrayList<>();
        for (int i = 0; i < parameterParts.size(); i++) {
            String parametersPart = parameterParts.get(i);
            if (parametersPart.startsWith(COMPOSITE_PARAMETER_OPEN_SYMBOL)) {
                StringBuilder compositeParameter = new StringBuilder();
                while (true) {
                    compositeParameter.append(parametersPart).append(" ");
                    parametersPart = parameterParts.get(++i);
                    if (parametersPart.endsWith(COMPOSITE_PARAMETER_CLOSE_SYMBOL)) {
                        compositeParameter.append(parametersPart);
                        break;
                    }
                }
                String regex = "[" + COMPOSITE_PARAMETER_OPEN_SYMBOL + COMPOSITE_PARAMETER_CLOSE_SYMBOL + "]";
                result.add(compositeParameter.toString().replaceAll(regex, ""));
            } else {
                result.add(parametersPart);
            }
        }
        result.removeAll(Arrays.asList(" ", "", null));
        return result;
    }

    private void print(String result) {
        System.out.println(result);
        System.out.println(REPORT_SEPARATOR);
    }
}
