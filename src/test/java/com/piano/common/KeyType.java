package com.piano.common;

public enum KeyType {
    BLACK("black"), WHITE("white");
    private final String keyType;

    KeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyType() {
        return keyType;
    }
}