package com.example.alineaBeC;

//import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class alineaCtest {

    private Map<String, Object> vars;
    JavascriptExecutor js;
    //String driverPath = "/home/eva/Downloads/geckodriver-v0.30.0-linux64/geckodriver";
    private WebDriver driver;

    
    @BeforeEach
    public void setUp() {
      vars = new HashMap<String, Object>();
      //System.setProperty("webdriver.gecko.driver", driverPath);
      driver = new ChromeDriver();
    }
    
    @Test
    public void test1() {
        js = (JavascriptExecutor) driver;
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(1057, 804));
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(4) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Manuel Diaz");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("Street Urban Night, n2");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Rennes");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("Rennes");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardMonth")).click();
    {
        WebElement element = driver.findElement(By.id("creditCardMonth"));
        Actions builder = new Actions(driver);
        builder.doubleClick(element).perform();
    }
        driver.findElement(By.id("creditCardMonth")).sendKeys("03");
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).click();
    {
        WebElement element = driver.findElement(By.id("creditCardYear"));
        Actions builder = new Actions(driver);
        builder.doubleClick(element).perform();
    }
        driver.findElement(By.id("creditCardYear")).sendKeys("2023");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
  }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
