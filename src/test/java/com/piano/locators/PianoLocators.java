package com.piano.locators;

public interface PianoLocators {

    String xpathBtn = "//android.widget.Button[@resource-id='com.nullapp.piano:id/%s']";
    String xpathBackBtn = "//android.widget.ImageButton[@resource-id='com.nullapp.piano:id/r_arrow']";

    String xpathBlackKey = "//android.widget.ImageButton[@resource-id='com.nullapp.piano:id/%s']";
    String xpathWhiteKey = "//android.widget.TextView[@text='%s']";

    String DELIMITER = "\\|";

    String SINGLE_KEYBOARD = "single_keyboard";
}
