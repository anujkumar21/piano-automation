package com.piano.test;

import com.piano.pages.Piano;
import com.piano.utils.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.piano.common.Direction.BACK;
import static com.piano.pages.Piano.SINGLE_KEYBOARD;

public class PlayPiano extends BaseTest {

    @Test(dataProvider = "songs")
    public void playSong(String song) throws InterruptedException {
        Piano piano = new Piano(driver);
        piano.clickOn(SINGLE_KEYBOARD);
        piano.shift(BACK, 1);
        piano.play(song);
    }

    @DataProvider
    public static Object[][] songs() {
        return new Object[][]{
                {"happy-birthday"},
                {"twinkle-twinkle"}
        };
    }
}


