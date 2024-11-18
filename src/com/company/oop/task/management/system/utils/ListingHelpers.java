package com.company.oop.task.management.system.utils;
import com.company.oop.task.management.system.models.contracts.Printable;
import com.company.oop.task.management.system.models.tasks.contracts.Task;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.JOIN_DELIMITER;

public class ListingHelpers {
    public static final String EMPTY_LIST = "The list is empty! Please, add an element first!";


//ToDo to optimize printing, possible can add Printable interface if need
    //Somehow have to find a way to optimize all printElement methods in all models

    public static <T extends Printable> String elementsToString(List<T> elements) {
        if (elements.isEmpty()){
            return EMPTY_LIST;
        }
        List<String> stringElements = new ArrayList<>();
        for (T element : elements) {
            stringElements.add(element.toString());
        }
        return String.join(JOIN_DELIMITER + System.lineSeparator(), stringElements).trim();
    }

    public static <T extends Task> String importantInfoToString(List<T> elements) {
        if (elements.isEmpty()){
            return EMPTY_LIST;
        }
        List<String> result = new ArrayList<>();
        for (T element : elements) {
            result.add(element.printImportantInfo());
        }

        return String.join(JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }

}
