package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Step2Page {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By heightBox = By.cssSelector("#txtHeight");
    private final By weightBox = By.cssSelector("#txtWeight");
    private final By saveStep2Btn = By.cssSelector("#btnSaveStep2");

    public Step2Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private void fillTextBox(By locator, String text) {
        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        textBox.sendKeys(text);
    }

    private void clickButton(By locator) {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));;
        button.click();
    }
    public void secondForm(String height, String weight) {
        System.out.println("***Empezando a completar el Formulario para índice de masa corporal***");
        fillTextBox(heightBox, height);
        fillTextBox(weightBox, weight);
        clickButton(saveStep2Btn);
    }

}