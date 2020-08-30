package com.piano.common;

public enum Direction {
    BACK("Back"), FORWARD("Forward");
    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
