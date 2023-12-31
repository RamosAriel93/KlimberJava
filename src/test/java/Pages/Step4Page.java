package Pages;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step4Page {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By nationalityContainer = By.id("select2-Nationality-container");
    private final By select2 = By.cssSelector("li.select2-results__option");
    private final By placeBirth = By.cssSelector("#PlaceOfBirth");
    private final By occupationBox = By.cssSelector("#txtOccupation");
    private final By salaryAnualBox = By.cssSelector("#txtSalaryAnual");
    private final By incomeContainer = By.cssSelector("#select2-txtAnnualIncome-container");
    private final By saveInfoBtn = By.cssSelector("#btnSaveInfo");

    public Step4Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fourthForm(String nationality, String placeB, String occupation, String salary, String income) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("***Completando segundos Formulario para registrarse***");
        clickElement(nationalityContainer);
        clickElementWithText(select2, nationality);
        fillTextBox(placeBirth, placeB);
        fillTextBox(occupationBox, occupation);
        fillTextBox(salaryAnualBox, salary);
        clickElement(incomeContainer);
        clickElementWithText(select2, income);
        wait.until(ExpectedConditions.elementToBeClickable(saveInfoBtn)).click();
    }

    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.click();
    }

    private void clickElementWithText(By locator, String text) {
        driver.findElements(locator).stream()
                .filter(element -> element.getText().contains(text))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    private void fillTextBox(By locator, String text) {
        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));;
        textBox.sendKeys(text);
    }
}