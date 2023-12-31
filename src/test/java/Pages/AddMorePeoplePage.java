package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddMorePeoplePage {
    private final WebDriver driver;
    private final By submitFinalBtn = By.cssSelector(".submitFinal");

    public AddMorePeoplePage(WebDriver driver) {
        this.driver = driver;
    }

    public void next() {
        System.out.println("***No agregamos más beneficiarios y continuamos***");
        clickElement(submitFinalBtn);
    }
    private void clickElement(By locator) {
        driver.findElement(locator).click();
    }
}