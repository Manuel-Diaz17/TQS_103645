package com.example.page_object_pattern.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.page_object_pattern.webpages.DeveloperApplyPage;
import com.example.page_object_pattern.webpages.HomePage;

class DemoPageObjectTest {

    WebDriver driver;

    @BeforeEach
    public void setup(){
        //use Chrome Driver
        driver = new ChromeDriver();
    }

    @Test
    public void applyAsDeveloper() {
        //Create object of HomePage Class
        HomePage home = new HomePage(driver);

        //Check if page is opened
        assertTrue(home.isPageOpened());

        home.clickOnFindFlightsButton();

        //Create object of DeveloperApplyPage
        DeveloperApplyPage applyPage =new DeveloperApplyPage(driver);

        //Check if page is opened
        assertTrue(applyPage.isPageOpened());

        //Fill up data
        applyPage.setName("Manuel Diaz");
        applyPage.setAddress("Street Urban Night, n2");
        applyPage.setCity("Rennes");
        applyPage.setState("Rennes");
        applyPage.setZipCode("12345");
        applyPage.setCreditCardNumber("123456789");
        applyPage.setCreditCardMonth("03");
        applyPage.setCreditCardYear("2023");
        applyPage.setNameOnCard("");

    }

    @AfterEach
    public void close(){
        driver.close();
    }
}
