package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Logout {


    WebDriver driver = new ChromeDriver();
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class);

    @BeforeEach
    public void leer() {
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
    }

    @Test
    @Order(7)
    void logout() {
        WebElement userName = driver.findElement(By.id("userName"));
        userName.sendKeys("YuriTester4");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123xFigaro!");

        WebElement login = wait.until(element -> driver.findElement(By.xpath("//button[@id='login']")));
        login.click();


        //Scroll
        WebElement footer = wait.until(element -> driver.findElement(By.id("gotoStore")));
        int deltaY = footer.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
        Actions action = new Actions(driver);
        action.moveToElement(wait.until(element -> driver.findElement(By.id("gotoStore"))));
        action.perform();

        //Ir a tienda
        WebElement bookStore = wait.until(element -> driver.findElement(By.id("gotoStore")));
        bookStore.click();

        //buscar libro
        WebElement searchBook = wait.until(element -> driver.findElement(By.className("form-control")));
        searchBook.sendKeys("Git Pocket Guide");

        //Seleccionar libro
        WebElement book1 = wait.until(element -> driver.findElement(By.linkText("Git Pocket Guide")));
        book1.click();

        WebElement footer2 = wait.until(element -> driver.findElement(By.tagName("footer")));
        int deltaY2 = footer2.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY2)
                .perform();


        //Agregar libro
        WebElement addBook = wait.until(element -> driver.findElement(By.xpath("//div[@class='text-right fullButton']")));
        addBook.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();

        //Regresar
        WebElement regresar = wait.until(element -> driver.findElement(By.xpath("//div[@class='text-left fullButton']//button[@id='addNewRecordButton']")));
        regresar.click();

        //buscar libro 2
        WebElement searchBook2 = wait.until(element -> driver.findElement(By.className("form-control")));
        searchBook2.sendKeys("Speaking JavaScript");

        //Seleccionar libro2
        WebElement book2 = wait.until(element -> driver.findElement(By.linkText("Speaking JavaScript")));
        book2.click();

        WebElement footer3 = wait.until(element -> driver.findElement(By.tagName("footer")));
        int deltaY3 = footer3.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY3)
                .perform();

        //Agregar libro 2
        WebElement addBook2 = wait.until(element -> driver.findElement(By.xpath("//div[@class='text-right fullButton']//button[@id='addNewRecordButton']")));
        addBook2.click();

        Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());
        String text2 = alert.getText();
        alert2.accept();

        //Regresar
        WebElement regresar2 = wait.until(element -> driver.findElement(By.xpath("//div[@class='text-left fullButton']")));
        regresar2.click();

        //Buscar libro2
        WebElement searchBook3 = wait.until(element -> driver.findElement(By.className("form-control")));
        searchBook3.click();


        //Seleccionar libro3
        WebElement book3 = wait.until(element -> driver.findElement(By.linkText("Learning JavaScript Design Patterns")));
        book3.click();
        WebElement footer4 = wait.until(element -> driver.findElement(By.tagName("footer")));
        int deltaY4 = footer4.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY4)
                .perform();
        //Agregar libro 3
        WebElement addBook3 = wait.until(element -> driver.findElement(By.xpath("//div[@class='text-right fullButton']")));
        addBook3.click();

        Alert alert3 = wait.until(ExpectedConditions.alertIsPresent());
        String text3 = alert.getText();
        alert3.accept();

        //Ir a mi perfil
        WebElement profile = wait.until(element -> driver.findElement(By.xpath("(//li[@id='item-3'])[5]")));
        profile.click();

        //Buscar un libro desde mi coleccion
        WebElement buscarEnColeccion = wait.until(element -> driver.findElement(By.className("form-control")));
        buscarEnColeccion.sendKeys("Speaking JavaScript");

        //Eliminar un libro de mi coleccion
        WebElement borrarLibro = wait.until(element -> driver.findElement(By.xpath("(//span[@id='delete-record-undefined'])")));
        borrarLibro.click();

        WebElement confirmar = wait.until(element -> driver.findElement(By.id("closeSmallModal-ok")));
        confirmar.click();

        Alert alert4 = wait.until(ExpectedConditions.alertIsPresent());
        String text4 = alert4.getText();
        alert4.accept();

        //Scroll
        WebElement footer5 = wait.until(element -> driver.findElement(By.tagName("footer")));
        int deltaY5 = footer5.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY5)
                .perform();

        //Eliminar todos los libros
        WebElement borrarTodo = wait.until(element -> driver.findElement(By.xpath("//div[@class='text-right button di']//button[@id='submit']")));
        borrarTodo.click();

        WebElement confirmar2 = wait.until(element -> driver.findElement(By.id("closeSmallModal-ok")));
        confirmar2.click();

        Alert alert5 = wait.until(ExpectedConditions.alertIsPresent());
        String text5 = alert5.getText();
        alert5.accept();

        WebElement iframe = driver.findElement(By.tagName("iframe"));
        new Actions(driver)
                .scrollToElement(iframe)
                .perform();

        WebElement logout = wait.until(element -> driver.findElement(By.xpath("(//button[@id='submit'])")));
        logout.click();

        WebElement welcome = wait.until(element -> driver.findElement(By.xpath("//h2[normalize-space()='Welcome,']")));

        assertEquals("Welcome,", welcome.getText());


    }

    @AfterEach
    void tearDown(){
        driver.close();
    }




}

