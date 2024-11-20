package com.company.oop.task.management.system.utils;
import com.company.oop.task.management.system.models.contracts.Printable;
import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.JOIN_DELIMITER;

public class ListingHelpers {
    public static final String EMPTY_LIST = "The list is empty! Please, add an element first!";

    public static <T extends Printable> String elementsToString(List<T> elements) {
       return elements.isEmpty() ? EMPTY_LIST :
               elements.stream()
                       .map(Object::toString)
                       .collect(Collectors.joining(JOIN_DELIMITER + System.lineSeparator()))
                       .trim();
    }

    public static <T extends Task> String listImportantInfoForAllTasks(List<T> elements) {
      return elements.isEmpty() ? EMPTY_LIST :
              elements.stream()
                      .map(Task::printImportantInfo)
                      .collect(Collectors.joining(JOIN_DELIMITER + System.lineSeparator()))
                      .trim();
    }

    public static <T extends Task> String listAllTasksFilteredByTitle(List<T> elements, String targetTitle) {
        return elements.isEmpty() ? EMPTY_LIST :
                elements.stream()
                        .filter(task -> task.getTitle().contains(targetTitle))
                        .map(Task::printImportantInfo)
                        .collect(Collectors.joining(JOIN_DELIMITER + System.lineSeparator()))
                        .trim();
    }

    public static <T extends Task> String listAllTasksSortedByTitle(List<T> elements) {
        return elements.isEmpty() ? EMPTY_LIST :
                elements.stream()
                        .sorted(Comparator.comparing(Task::getTitle))
                        .map(Task::printImportantInfo)
                        .collect(Collectors.joining(JOIN_DELIMITER + System.lineSeparator()))
                        .trim();
    }

    public static <T extends Task> String listAllTasksSortedAndFilteredByTitle(List<T> elements, String targetTitle) {
        return elements.isEmpty() ? EMPTY_LIST :
                elements.stream()
                        .filter(task -> task.getTitle().contains(targetTitle))
                        .sorted(Comparator.comparing(Task::getTitle))
                        .map(Task::printImportantInfo)
                        .collect(Collectors.joining(JOIN_DELIMITER + System.lineSeparator()))
                        .trim();
    }

}
