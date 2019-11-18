package com.improving.Enums;

public enum Faces {
    Zero(1, "Zero"),
    One(1, "One"),
    Two(2, "Two"),
    Three(3, "Three"),
    Four(4, "Four"),
    Five(5, "Five"),
    Six(6, "Six"),
    Seven(7, "Seven"),
    Eight(8, "Eight"),
    Nine(9, "Nine"),
    Reverse(20, "Reverse"),
    Skip(20, "Skip"),
    Draw2(20, "Draw 2"),
    Draw4(50, "Draw 4"),
    Wild(50, "Wild");

    private int value;
    private String name;

    Faces(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
