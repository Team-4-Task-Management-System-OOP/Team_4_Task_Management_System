package com.company.oop.task.management.system.utils;



import com.company.oop.task.management.system.commands.CommandsConstants;
import com.company.oop.task.management.system.models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {


//ToDo to optimize printing, possible can add Printable interface if need

    public static <T extends Printable> String elementsToString(List<T> elements) {
        List<String> stringElements = new ArrayList<>();
        for (T element : elements) {
            stringElements.add(element.toString());
        }
        return String.join(CommandsConstants.JOIN_DELIMITER + System.lineSeparator(), stringElements).trim();
    }

}
