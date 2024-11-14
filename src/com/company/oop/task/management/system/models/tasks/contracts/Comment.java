package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.contracts.Printable;

public interface Comment extends Printable {

    String getContent();

    String getAuthor();

}
