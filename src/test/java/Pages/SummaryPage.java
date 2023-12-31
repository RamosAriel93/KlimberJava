package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SummaryPage {
    private final WebDriver driver;
    private final By tcBtn = By.cssSelector("#chkTC");
    private final By summaryBtn = By.cssSelector("#btnSummarySubmit");

    public SummaryPage(WebDriver driver) {
        this.driver = driver;
    }
    private void clickElement(By locator) {
        driver.findElement(locator).click();
    }
    private void checkCheckbox(By locator) {
        if (!driver.findElement(locator).isSelected()) {
            driver.findElement(locator).click();
        }
    }
    public void acept() {
        System.out.println("***Aceptamos los términos y condiciones***");
        checkCheckbox(tcBtn);
        clickElement(summaryBtn);
    }
}
