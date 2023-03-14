package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class Login {

    WebDriver driver = new ChromeDriver();
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class);


    @BeforeEach
    public void leer(){
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
    }

    @Test
    @Order(1)
    void books() {
        WebElement userName = driver.findElement(By.id("userName"));
        userName.sendKeys("YuriTester4");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123xFigaro!");

        WebElement login = wait.until(element -> driver.findElement(By.xpath("//button[@id='login']")));
        login.click();

        WebElement userLabel = wait.until(element -> driver.findElement(By.xpath("//label[@id='userName-value']")));

        assertEquals("YuriTester4", userLabel.getText());
    }



    @AfterEach
    void tearDown(){
        driver.close();
    }


}

