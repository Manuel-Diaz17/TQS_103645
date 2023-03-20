/*
 * (C) Copyright 2020 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BlazeDemoSteps {

    private final WebDriver driver = new ChromeDriver();

    @When("I navigate to {string}")
    public void NavigateTo(String website) {
        driver.get(website);
    }

    @Then("The title of the page should be {string}")
    public void TitleOfThePage(String pageTitle) {
        assertEquals(driver.getTitle(), pageTitle);
    }

    @And("My departure city is {string}")
    public void DepartureCity(String city) {
        Select dropdown = new Select(driver.findElement(By.xpath("/html/body/div[3]/form/select[1]")));
        dropdown.selectByValue(city);
    }

    @And("My destination city is {string}")
    public void DestinationCity(String city) {
        Select dropdown = new Select(driver.findElement(By.xpath("/html/body/div[3]/form/select[2]")));
        dropdown.selectByValue(city);
    }

    @And("I click {string}")
    public void Click(String value) {
        driver.findElement(By.xpath("//input[@value='"+value+"']")).click();
    }

    @Then("I should be shown results of {string}")
    public void ResultsOf(String resultsOf) {
        assertThat(driver.findElement(By.cssSelector("h3")).getText(), containsString(resultsOf));
    }

    @And("My Name is {string}")
    public void MyName(String name) {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys(name);
    }

    @And("My Address is {string}")
    public void MyAddress(String address) {
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys(address);
    }

    @And("My City is {string}")
    public void MyCity(String city) {
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys(city);
    }

    @And("My State is {string}")
    public void MyState(String state) {
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("Aveiro");
    }

    @And("My Zip Code is {int}")
    public void MyZipCode(Integer zipCode) {
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys(zipCode.toString());
    }

    @And("My Credit Card Number is {int}")
    public void MyCreditCardNumber(Integer creditCardNumber) {
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys(creditCardNumber.toString());
    }

    @And("My Name on Card is {string}")
    public void MyNameOnCard(String nameOnCard) {
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys(nameOnCard);
    }

    @Then("The purchase should be confirmed")
    public void thePurchaseShouldBeConfirmed() {
        assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "BlazeDemo Confirmation");
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}
