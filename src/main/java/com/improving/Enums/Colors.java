package com.improving.Enums;

public enum Colors {
    Red("Red"),
    Green("Green"),
    Yellow("Yellow"),
    Blue("Blue"),
    Wild("Wild");

    private String color;

    Colors(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color;
    }
}
