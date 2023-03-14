package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchABook {

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
    @Order(2)

    void search() {
        WebElement userName = driver.findElement(By.id("userName"));
        userName.sendKeys("YuriTester4");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123xFigaro!");

        WebElement login = wait.until(element -> driver.findElement(By.xpath("//button[@id='login']")));
        login.click();

        WebElement footer = wait.until(element -> driver.findElement(By.id("gotoStore")));
        int deltaY = footer.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();

        Actions action = new Actions(driver);
        action.moveToElement(wait.until(element -> driver.findElement(By.id("gotoStore"))));
        action.perform();

        //Ir a tienda
        WebElement bookStoree = wait.until(element -> driver.findElement(By.id("gotoStore")));
        bookStoree.click();

        //buscar un libro
        WebElement buscarEnTienda = wait.until(element -> driver.findElement(By.className("form-control")));
        buscarEnTienda.sendKeys("Git Pocket Guide");

        WebElement searchBook = wait.until(element -> driver.findElement(By.linkText("Git Pocket Guide")));

        assertEquals("Git Pocket Guide", searchBook.getText());
    }

    @AfterEach
    void tearDown(){
        driver.close();
    }


}

