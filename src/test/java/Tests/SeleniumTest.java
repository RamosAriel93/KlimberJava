package Tests;
import Pages.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class SeleniumTest {
    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;
    private static ExtentTest extentTest;
    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        sparkReporter = new ExtentSparkReporter("Reporte.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setOfflineMode(true);
        sparkReporter.config().setDocumentTitle("Simple Automation Report");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setEncoding("UTF-8");
    }

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumJava\\Challenge\\drivers\\chromedriver.exe"); // Reemplaza con la ubicación de tu chromedriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://purchase-testing.klimber.com/ar/GroupLife/Index");
        System.out.println("----Iniciando Test----");
    }



    @Test
    public void testHappyPath() throws InterruptedException {
        extentTest = extent.createTest("testHappyPath");
        try {
        IndexPage pageIndex = new IndexPage(driver);
        pageIndex.indexForm("01/07/1980", "11", "23496789", 500);
        Step2Page step2Page = new Step2Page(driver);
        step2Page.secondForm("180", "90");
        Utils utils = new Utils();
        String randomEmail = utils.emailRandom();
        int randomDni = utils.dniRandom();
        RegisterUserPage registerUserPage = new RegisterUserPage(driver);
        registerUserPage.thirdForm("Juan", "Ramirez", String.valueOf(randomDni), "Femenino", "Femenino", "Soltera/o",
                String.valueOf(randomEmail), "1234567890", "False", "123", "01", "02",
                "1744");
        Step4Page page4 = new Step4Page(driver);
        page4.fourthForm("Argentina", "Moreno", "Empleado", "6000000", "Sueldo");
        CompleteCardPage pageCard = new CompleteCardPage(driver);
        pageCard.creditCard("4548812049400004");
        AddMorePeoplePage pageAdd = new AddMorePeoplePage(driver);
        pageAdd.next();
        SummaryPage pageSummary = new SummaryPage(driver);
        pageSummary.acept();
        //Finish
        System.out.println("***Verificamos si el formulario se creó correctamente***");
        WebElement titleElement = driver.findElement(By.cssSelector(".form-group-custom > .title"));
        String actualText = titleElement.getText().trim();
        String expectedText = "¡Felicitaciones!";

        Assert.assertEquals(actualText, expectedText, "El formulario no se completó correctamente");
        System.out.println("***Formulario completado con éxito***");
        extentTest.log(Status.PASS, "***Formulario completado con éxito***");
        Thread.sleep(2000);
    } catch (Exception ex) {
            extentTest.info("La Prueba ha fallado");
        extentTest.log(Status.FAIL, ex);
        throw ex;
    }
}
    @Test
    public void testEmptyDateOfBirthField() throws InterruptedException {
        extentTest = extent.createTest("testEmptyDateOfBirthField");
        try {
        System.out.println("Comprobando que no se pueda seguir sin dato de nacimiento");
        IndexPage pageIndex = new IndexPage(driver);
        pageIndex.indexForm(" ", "11", "23496789", 500);
        String expectedText = "Falta completar este campo.";
        String actualText = pageIndex.getTextPopUp();
        Assert.assertEquals(expectedText, actualText);
        System.out.println("***Corroboración exitosa***");
        extentTest.log(Status.PASS, "***Corroboración exitosa***");
        } catch (Exception ex) {
            extentTest.info("La Prueba ha fallado");
        extentTest.log(Status.FAIL, ex);
        throw ex;
     }
    }

    @Test
    public void testInvalidDateOfBirthField() {
        extentTest = extent.createTest("testInvalidDateOfBirthField");
        try{
        System.out.println("Comprobando fecha de nacimiento inválida");
        IndexPage pageIndex = new IndexPage(driver);
        String expectedText = "La fecha que ingresaste es inválida";
        pageIndex.indexForm("14/10/1066", "11", "23496789", 500);
        String actualText = pageIndex.getTextPopUp();
        Assert.assertEquals(expectedText, actualText);
        System.out.println("***Corroboración exitosa de fecha de nacimiento inválida***");
        extentTest.log(Status.PASS, "***Corroboración exitosa de fecha de nacimiento inválida***");
        } catch (Exception ex) {
            extentTest.info("La Prueba ha fallado");
            extentTest.log(Status.FAIL, ex);
            throw ex;
        }
    }
    @Test
    public void testPhoneNumberOnlyNumbers() {
        extentTest = extent.createTest("testPhoneNumberOnlyNumbers");
        try {
        System.out.println("Comprobando que el campo solo admita números");
        IndexPage pageIndex = new IndexPage(driver);
        String expectedText = "Por favor, ingresá solo números.";
        pageIndex.indexForm("14/10/1966", "11", "asdf", 500);
        String actualText = pageIndex.getTextPopUp();
        Assert.assertEquals(expectedText, actualText);
        System.out.println("***Corroboración exitosa de campo que solo admite números***");
        extentTest.log(Status.PASS, "***Corroboración exitosa de campo que solo admite números***");
        } catch (Exception ex) {
            extentTest.info("La Prueba ha fallado");
            extentTest.log(Status.FAIL, ex);
            throw ex;
        }
    }
    @Test
    public void testAreaCodeSupportsNumbers() {
        extentTest = extent.createTest("testAreaCodeSupportsNumbers");
        try {
        System.out.println("Comprobando que el campo del código de área solo admite números");
        IndexPage pageIndex = new IndexPage(driver);
        String expectedText = "Por favor, ingresá solo números.";
        pageIndex.indexForm("14/10/1966", "asdf", "23496789", 500);
        String actualText = pageIndex.getTextPopUp();
        Assert.assertEquals(expectedText, actualText);
        System.out.println("***Corroboración exitosa de código de área que solo admite números***");
        extentTest.log(Status.PASS, "***Corroboración exitosa de código de área que solo admite números***");
        } catch (Exception ex) {
            extentTest.info("La Prueba ha fallado");
            extentTest.log(Status.FAIL, ex);
            throw ex;
        }
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("----Finalizando Test-----");
        driver.quit();

    }
    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }

}

