package com.company.oop.task.management.system.utils;
import com.company.oop.task.management.system.models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.task.management.system.commands.utils.CommandsConstants.JOIN_DELIMITER;

public class ListingHelpers {


//ToDo to optimize printing, possible can add Printable interface if need

    public static <T extends Printable> String elementsToString(List<T> elements) {
        List<String> stringElements = new ArrayList<>();
        for (T element : elements) {
            stringElements.add(element.toString());
        }
        return String.join(JOIN_DELIMITER + System.lineSeparator(), stringElements).trim();
    }

}
