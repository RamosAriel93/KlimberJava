package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterUserPage {
    private final WebDriver driver;
    private final By nameBox = By.cssSelector("#Name");
    private final By surNameBox = By.cssSelector("#Surname");
    private final By idNumberBox = By.cssSelector("#ID_Number");
    private final By genderContainer = By.cssSelector("#select2-Gender-container");
    private final By select2 = By.cssSelector("li.select2-results__option");
    private final By identificationGender = By.cssSelector("#select2-IdentificationGenderType-container");
    private final By civilStatus = By.cssSelector("#select2-CivilStatus-container");
    private final By emailBox = By.cssSelector("#txtEmail");
    private final By passWordBox = By.cssSelector("#Password");
    private final By streetBox = By.cssSelector("#Street");
    private final By houseNumberBox = By.cssSelector("#HouseNumber");
    private final By floorBox = By.cssSelector("#Floor");
    private final By apartamentBox = By.cssSelector("#Apartment");
    private final By zipCodeBox = By.cssSelector("#zipCode");
    private final By cityContainer = By.cssSelector("#select2-city-container");
    private final By selectCity = By.xpath("//li[contains(@class, 'select2-results__option')and contains(text(), 'Cuartel V')]");
    private final By registerBtn = By.xpath("//button[contains(@id, 'btnRegister')]");

    public RegisterUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void thirdForm(String name, String surName, String idNumber, String gender, String identification, String statusCivil,
                          String email, String passWord, String street, String houseNumber, String floor, String apartament,
                          String zipCode) {

        System.out.println("***Completando datos para registrarse***");
        fillTextBox(nameBox, name);
        fillTextBox(surNameBox, surName);
        fillTextBox(idNumberBox, idNumber);
        clickElement(genderContainer);
        clickElementWithText(select2, gender);
        clickElement(identificationGender);
        clickElementWithText(select2, identification);
        clickElement(civilStatus);
        clickElementWithText(select2, statusCivil);
        fillTextBox(emailBox, email);
        fillTextBox(passWordBox, passWord);
        fillTextBox(streetBox, street);
        fillTextBox(houseNumberBox, houseNumber);
        fillTextBox(floorBox, floor);
        fillTextBox(apartamentBox, apartament);
        fillTextBox(zipCodeBox, zipCode);
        clickElement(cityContainer);
        clickElement(registerBtn);
    }

    private void fillTextBox(By locator, String text) {
        WebElement textBox = driver.findElement(locator);
        textBox.sendKeys(text);
    }

    private void clickElement(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    private void clickElementWithText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        driver.findElements(locator).stream()
                .filter(element -> element.getText().contains(text))
                .findFirst()
                .ifPresent(WebElement::click);
    }
}

