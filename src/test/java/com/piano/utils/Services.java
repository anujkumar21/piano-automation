package com.piano.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class Services {

    public WebDriverWait wait;
    AppiumDriver<MobileElement> driver;

    public Services(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void waitForElementByXpath(String xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void clickOnElementByXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void pressHold(String xpath, int time) throws InterruptedException {
        Actions clickHoldAction = new Actions(driver);
        MobileElement mobileElement = driver.findElement(By.xpath(xpath));
        clickHoldAction.clickAndHold(mobileElement).build().perform();
        sleep(time);
        clickHoldAction.click(mobileElement).build().perform();
    }

}