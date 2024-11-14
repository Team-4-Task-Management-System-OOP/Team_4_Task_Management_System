package com.company.oop.task.management.system.core;

import com.company.oop.task.management.system.commands.contracts.Command;
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
    private static final String COMMENT_OPEN_SYMBOL = "{{";
    private static final String COMMENT_CLOSE_SYMBOL = "}}";
    private static final String REPORT_SEPARATOR = "####################";

    private final CommandFactory commandFactory;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public TaskManagementSystemEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
    }



    @Override
    public void start() {
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
        // CreatePerson Dimitar
        List<String> parameters = extractCommandParameters(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, taskManagementSystemRepository);
        String executionResult = command.execute(parameters);
        print(executionResult);
    }

    private String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
    }

    private List<String> extractCommandParameters(String inputLine) {
        if (inputLine.contains(COMMENT_OPEN_SYMBOL)) {
            return extractCommentParameters(inputLine);
        }
        String[] commandParts = inputLine.split(MAIN_SPLIT_SYMBOL);
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }

    private List<String> extractCommentParameters(String fullCommand) {
        int indexOfFirstSeparator = fullCommand.indexOf(MAIN_SPLIT_SYMBOL);
        int indexOfOpenComment = fullCommand.indexOf(COMMENT_OPEN_SYMBOL);
        int indexOfCloseComment = fullCommand.indexOf(COMMENT_CLOSE_SYMBOL);
        List<String> parameters = new ArrayList<>();
//        if (indexOfOpenComment >= 0) {
//            parameters.add(fullCommand.substring(indexOfOpenComment + COMMENT_OPEN_SYMBOL.length(), indexOfCloseComment));
//            fullCommand = fullCommand.replaceAll("\\{\\{.+(?=}})}}", "");
//        }
        List<String> result = new ArrayList<>(Arrays.asList(fullCommand.substring(indexOfFirstSeparator + 1).split(MAIN_SPLIT_SYMBOL)));
        result.removeAll(Arrays.asList(" ", "", null));
        parameters.addAll(result);
        return parameters;
    }

    private void print(String result) {
        System.out.println(result);
        System.out.println(REPORT_SEPARATOR);
    }
}
