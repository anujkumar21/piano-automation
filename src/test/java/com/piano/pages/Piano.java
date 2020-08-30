package com.piano.pages;

import com.piano.common.Direction;
import com.piano.common.KeyType;
import com.piano.locators.PianoLocators;
import com.piano.utils.Services;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.piano.common.KeyType.BLACK;
import static com.piano.common.KeyType.WHITE;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.Thread.sleep;

public class Piano implements PianoLocators {
    private static final String DELIMITER = "\\|";
    private static int keyShift = 0;

    public static final String SINGLE_KEYBOARD = "single_keyboard";

    AppiumDriver<MobileElement> driver;
    Services services;

    public Piano(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        services = new Services(driver);
    }

    public void clickOn(String btn) throws InterruptedException {
        services.clickOnElementByXpath(format(xpathBtn, btn));
        services.waitForElementByXpath(format(xpathWhiteKey, "C5"));
    }

    private void pressHold(KeyType keyType, String note, int time) throws InterruptedException {

        services.pressHold(format(keyType == WHITE ? xpathWhiteKey : xpathBlackKey, note), time);
    }

    public void play(String key, int time) throws InterruptedException {
        music(key, time);
    }

    public void play(String key, int time, int occur) throws InterruptedException {
        for (int i = 0; i < occur; i++) {
            music(key, time);
        }
    }

    private void music(String key, int time) throws InterruptedException {
        System.out.println(key);
        switch (key) {
            case "B-":
                pressHold(WHITE, "B3", time);
                break;
            case "C":
                pressHold(WHITE, "C4", time);
                break;
            case "C#":
                pressHold(BLACK, "btn_blackkey_" + (2 + keyShift), time);
                break;
            case "D":
                pressHold(WHITE, "D4", time);
                break;
            case "D#":
                pressHold(BLACK, "btn_blackkey_" + (3 + keyShift), time);
                break;
            case "E":
                pressHold(WHITE, "E4", time);
                break;
            case "F":
                pressHold(WHITE, "F4", time);
                break;
            case "F#":
                pressHold(BLACK, "btn_blackkey_" + (5 + keyShift), time);
                break;
            case "G":
                pressHold(WHITE, "G4", time);
                break;
            case "G#":
                pressHold(BLACK, "btn_blackkey_" + (6 + keyShift), time);
                break;
            case "A":
                pressHold(WHITE, "A4", time);
                break;
            case "A#":
                pressHold(BLACK, "btn_blackkey_" + (7 + keyShift), time);
                break;
            case "B":
                pressHold(WHITE, "B4", time);
                break;
            case "C'":
                pressHold(WHITE, "C5", time);
                break;
            case "C'#":
                pressHold(BLACK, "btn_blackkey_" + (9 + keyShift), time);
                break;
            case "D'":
                pressHold(WHITE, "D5", time);
                break;
            case "D'#":
                pressHold(BLACK, "btn_blackkey_" + (10 + keyShift), time);
                break;
            case "E'":
                pressHold(WHITE, "E5", time);
                break;
            case "_":
                pauseFor(time);
                break;
            default:
                System.out.println("no match");
        }
    }

    private void pauseFor(int time) throws InterruptedException {
        sleep(time);
    }

    public List<String> getNotesFromFile(String filename) {
        String fileName = filename + ".txt";
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("!"))
                    for (String n : line.split(" ")) {
                        list.add(n);
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void shift(Direction direction, int numberOfTimes) {

        MobileElement element = driver.findElement(By.xpath(xpathBackBtn));
        for (int i = 0; i < numberOfTimes; i++) {
            element.click();
            keyShift++;
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
