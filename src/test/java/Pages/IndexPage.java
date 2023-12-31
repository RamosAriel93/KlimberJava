package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IndexPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By birthDayInput = By.cssSelector("#BirthdayStep1");
    private final By provinceBtn = By.id("select2-province-container");
    ;

    private final By provinceOption = By.xpath("//li[contains(@class, 'select2-results__option') and contains(text(), 'Buenos Aires')]");
    private final By phoneCodeBox = By.cssSelector("#txtPhoneCode");
    private final By phoneNumberBox = By.cssSelector("#txtPhoneNumber");
    private final By slider = By.cssSelector(".min-slider-handle");
    private final By saveStep1Btn = By.cssSelector("#btnSaveStep1");

    public IndexPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private void fillInput(By locator, String text) {
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        inputElement.sendKeys(text);
    }
    private void clickButton(By locator) {
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        buttonElement.click();
    }
    public void indexForm(String birthDay, String phoneCode, String phoneNumber, int sliderMove) {
        System.out.println("***Empezando a completar el primer Formulario***");
        fillInput(birthDayInput, birthDay);
        clickButton(provinceBtn);
        clickButton(provinceOption);
        fillInput(phoneCodeBox, phoneCode);
        fillInput(phoneNumberBox, phoneNumber);
        moveSlider(slider, sliderMove);
        //clickButton(saveStep1Btn);
    }

    public String getTextPopUp() {
        WebElement popUp = driver.findElement(By.cssSelector(".ui-pnotify-text"));
        return popUp.getText().trim();
    }



    private void moveSlider(By locator, int xOffset) {
        WebElement sliderElement = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.clickAndHold(sliderElement).moveByOffset(xOffset, 0).release().build().perform();
    }


}
