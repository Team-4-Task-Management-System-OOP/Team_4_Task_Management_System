package com.company.oop.task.management.system.models.tasks.enums;

public enum PriorityType {
    HIGH, MEDIUM, LOW;

    @Override
    public String toString() {
        switch (this) {
            case HIGH:
                return "High";
            case MEDIUM:
                return "Medium";
            case LOW:
                return "Low";
            default:
                throw new IllegalArgumentException("Invalid Priority. Priority can be: High, Medium, Low.");
        }
    }

}
