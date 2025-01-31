package cucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import static dataProviders.configFileReader.getPropertyValue;
import static dataProviders.datasetFileReader.getDatasetValue;
import static dataProviders.repositoryFileReader.constructElement;
import static dataProviders.repositoryFileReader.findElementRepo;
import static org.junit.Assert.assertEquals;

import java.util.UUID;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class WebSteps {

    WebDriver driver;
    private String randomEmpName;
    private String randomUserName;
    private String randomEmail;
    private String generatedNumber;


    private Scenario scenario;


    @Before
    public void setup(Scenario scenario){
        String browser = getPropertyValue("browser");
        switch (browser){
            case "chrome" -> {
                driver = WebDriverManager.chromedriver().create();
            }
            case "firefox" -> {
                driver = WebDriverManager.firefoxdriver().create();
            }
            case "edge" -> {
                driver = WebDriverManager.edgedriver().create();
            }
            default -> throw new RuntimeException("Browser not supported");
        }
        // Maximize the browser window
        driver.manage().window().maximize();

        scenario.log("Scenario executed on " + browser + " browser");
        this.scenario = scenario;
    }

    @After
    public void afterScenario (Scenario scenario) throws IOException {
        //Take Screenshot and attached to the report
        if (scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
            scenario.attach(fileContent, "image/png", "Failed step screenshot");
        }

            driver.quit();
    }

    @When("I type {string} to the {string} And Enter")
    public void iTypeToTheAndEnter(String text, String locator) {
        By element = constructElement(findElementRepo(locator));
        WebElement inputElement = driver.findElement(element);
        inputElement.sendKeys(text);
        inputElement.sendKeys(Keys.RETURN);
    }

    @Given("I have opened the system")
    public void iHaveOpenedTheSystem() {
        driver.get(getPropertyValue("baseURL"));
    }


    @And("I type {string} to the {string}")
    public void iTypeToThe(String text, String locator) {
        By element = constructElement(findElementRepo(locator));
        driver.findElement(element).sendKeys(text);
    }

    @And("I click on {string}")
    public void iClickOn(String locator) {
        By element = constructElement(findElementRepo(locator));
        driver.findElement(element).click();
    }

    @Then("I see the exact {string} display on {string}")
    public void iSeeTheExactDisplayOn(String text, String locator) {
        By element = constructElement(findElementRepo(locator));
        //get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(50000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        assertEquals("Assert field text is not match", value, driver.findElement(element).getText());
    }

    @And("I wait few seconds")
    public void iWaitFewSeconds() throws InterruptedException {
        Thread.sleep(3000); //Waiting Time
    }

    @Given("I generate a random UserName to the {string}") //Generate Random Names
    public void iGenerateARandomUserName(String UserName) {
        List<String> empNames = Arrays.asList(
                "Sahan",
                "Vidura",
                "Malith",
                "Vinnath",
                "Venura",
                "Beesara",
                "Vidath",
                "Haritha",
                "Pramith",
                "Savindu"
        );

        Random random = new Random();
        int index = random.nextInt(empNames.size());
        randomUserName = empNames.get(index);
        By element = constructElement(findElementRepo(UserName));
        driver.findElement(element).sendKeys(String.valueOf(randomUserName));
    }

    public String fetchedString;
    public String savedValue;

    @Given("I have a saved value {string}")
    public void i_have_a_saved_value(String value) {
        savedValue = value;
    }

    @Given("I fetch a string from the web {string}")
    public void iFetchAStringFromTheWeb(String locator) {
        By element = constructElement(findElementRepo(locator));
        WebElement inputElement = driver.findElement(element);
        fetchedString = inputElement.getText();
        System.out.println(fetchedString);
    }

    @When("I compare the fetched string with the saved value")
    public void i_compare_the_fetched_string_with_the_saved_value() {
        // Compare fetched string with saved value
        // Example comparison logic
        if (savedValue.equals(fetchedString)) {
            System.out.println("Comparison successful");
        } else {
            System.out.println("Comparison unsuccessful");
            driver.quit();
        }
    }

    @Given("I generate a random email address to the {string}") //Generate Random Emails
    public void iGenerateARandomEmailAddress(String random_Mail) {
        randomEmail = "user" + UUID.randomUUID().toString() + "@example.com";
        System.out.println("Generated Email: " + randomEmail);
        By element = constructElement(findElementRepo(random_Mail));
        driver.findElement(element).sendKeys(String.valueOf(randomEmail));
    }

    @Given("I generate a random mobile number to the {string}")   //Generate Random Mobile number
    public void iGenerateARandomMobileNumber(String p_number) {
        generatedNumber = "07" + (new Random().nextInt(900000000) + 100000000);
        System.out.println("Generated Mobile Number: " + generatedNumber);
        By element = constructElement(findElementRepo(p_number));
        driver.findElement(element).sendKeys(String.valueOf(generatedNumber));
    }

    @And("I hard click {string} and press Enter")
    public void i_hard_click_and_press_enter(String locator) {
        // Locate the element using the provided locator
        By elementLocator = constructElement(findElementRepo(locator));
        WebElement element = driver.findElement(elementLocator);

        // Create an instance of Actions class to perform the actions
        Actions actions = new Actions(driver);

        // Move to the element and perform a hard click (click while ensuring visibility)
        actions.moveToElement(element).click().perform();

        // Press Enter after clicking
        actions.sendKeys(Keys.ENTER).perform();
    }

    @Given("user uploads the Image file {string}")    //File Uploading Method
    public void userUploadsTheImage(String locator) throws AWTException {
        // Locate the file input element using the locator
        By element = constructElement(findElementRepo(locator));
        WebElement inputElement = driver.findElement(element);

        // Perform a hard click on the file input element using Actions class
        Actions actions = new Actions(driver);
        actions.moveToElement(inputElement).click().perform();  // Hard click

        // Handle the file upload dialog
        String filePath = "C:\\Users\\User\\images.jpeg";  // Update with actual path of the image
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // Use Robot class to interact with the file dialog
        Robot robot = new Robot();
        robot.delay(1000); // Delay to allow time for the dialog to appear
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000); // Delay to ensure the file path is pasted
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }












}

