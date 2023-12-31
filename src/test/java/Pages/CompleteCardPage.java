package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompleteCardPage {
    private final WebDriver driver;
    private final By cardNumberBox = By.cssSelector("#CardNumber");
    private final By submitStepBtn = By.cssSelector("#btnSubmitStep4");

    public CompleteCardPage(WebDriver driver) {
        this.driver = driver;
    }

    private void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    private void fillTextBox(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void creditCard(String cardNumber) {
        System.out.println("***Ingresando método de pago***");
        fillTextBox(cardNumberBox, cardNumber);
        clickElement(submitStepBtn);
    }
}

