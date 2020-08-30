package com.piano.pages;

import com.piano.common.Direction;
import com.piano.common.KeyType;
import com.piano.locators.PianoLocators;
import com.piano.utils.Services;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static com.piano.common.Direction.BACK;
import static com.piano.common.KeyType.BLACK;
import static com.piano.common.KeyType.WHITE;
import static com.piano.utils.ReadSongNotes.getNotesFromFile;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.Thread.sleep;

public class Piano implements PianoLocators {
    private static int keyShift = 0;

    AppiumDriver<MobileElement> driver;
    Services services;

    public Piano(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        services = new Services(driver);
    }

    public void clickOn(String btn) {
        services.clickOnElementByXpath(format(xpathBtn, btn));
        services.waitForElementByXpath(format(xpathWhiteKey, "C5"));
    }

    private void hit(KeyType keyType, String note, int time) throws InterruptedException {
        services.pressHold(format(keyType == WHITE ? xpathWhiteKey : xpathBlackKey, note), time);
    }

    private void play(String key, int time) throws InterruptedException {
        music(key, time);
    }

    private void play(String key, int time, int occur) throws InterruptedException {
        for (int i = 0; i < occur; i++) {
            music(key, time);
        }
    }

    private void music(String key, int time) throws InterruptedException {
        System.out.println(format("Press note '%s' for %d seconds.", key, time));
        switch (key) {
            case "B-":
                hit(WHITE, "B3", time);
                break;
            case "C":
                hit(WHITE, "C4", time);
                break;
            case "C#":
                hit(BLACK, "btn_blackkey_" + (2 + keyShift), time);
                break;
            case "D":
                hit(WHITE, "D4", time);
                break;
            case "D#":
                hit(BLACK, "btn_blackkey_" + (3 + keyShift), time);
                break;
            case "E":
                hit(WHITE, "E4", time);
                break;
            case "F":
                hit(WHITE, "F4", time);
                break;
            case "F#":
                hit(BLACK, "btn_blackkey_" + (5 + keyShift), time);
                break;
            case "G":
                hit(WHITE, "G4", time);
                break;
            case "G#":
                hit(BLACK, "btn_blackkey_" + (6 + keyShift), time);
                break;
            case "A":
                hit(WHITE, "A4", time);
                break;
            case "A#":
                hit(BLACK, "btn_blackkey_" + (7 + keyShift), time);
                break;
            case "B":
                hit(WHITE, "B4", time);
                break;
            case "C'":
                hit(WHITE, "C5", time);
                break;
            case "C'#":
                hit(BLACK, "btn_blackkey_" + (9 + keyShift), time);
                break;
            case "D'":
                hit(WHITE, "D5", time);
                break;
            case "D'#":
                hit(BLACK, "btn_blackkey_" + (10 + keyShift), time);
                break;
            case "E'":
                hit(WHITE, "E5", time);
                break;
            case "_":
                sleep(time);
                break;
            default:
                System.out.println("no match");
        }
    }

    public void shift(Direction direction, int numberOfTimes) {
        if (direction == BACK) {
            MobileElement element = driver.findElement(By.xpath(xpathBackBtn));
            for (int i = 0; i < numberOfTimes; i++) {
                element.click();
                keyShift++;
            }
        }
    }

    public void play(String filename) throws InterruptedException {
        for (String note : getNotesFromFile(filename)) {
            String[] arr = note.split(DELIMITER);
            if (arr.length == 3) play(arr[0], parseInt(arr[1]), parseInt(arr[2]));
            else play(arr[0], parseInt(arr[1]));
        }
    }
}
