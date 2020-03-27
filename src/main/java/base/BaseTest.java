package base;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.reporters.SuiteHTMLReporter;
import org.testng.reporters.XMLReporter;
import pages.MainPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static constans.Wait.TIME_EXPLICIT_WAIT;
import static constans.Wait.TIME_IMPLICIT_WAIT;
import static org.testng.Assert.*;


@Listeners({SuiteHTMLReporter.class, XMLReporter.class})
public abstract class BaseTest {

    private static final String base_URL = "https://demo.nopcommerce.com/";
    private WebDriver driver;
    private Actions actions;
    private Logger logger;

    public WebDriver getDriver() {
        return driver;
    }


    private void setOS() {
        String osDriverName;
        String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((os.contains("mac")) || (os.contains("darwin"))) {
            osDriverName = "mac64";
        } else if (os.contains("win")) {
            osDriverName = "win32.exe";
        } else {
            throw new RuntimeException("Cannot define your system" + os);
        }
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome_" + osDriverName);
    }

    @BeforeTest
    protected void initializeDriver() {
        configureDriver();
        logger = Logger.getLogger("nopCommerce");
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        actions = new Actions(driver);
        turnOnImplicitWait();
    }

    private void configureDriver() {
        setOS();

        try {
            ChromeOptions chromeOptions = new ChromeOptions().addArguments("disable-infobars", "--disable-extensions", "test-type");
            chromeOptions.setCapability("pageLoadStrategy", "none");
            driver = new ChromeDriver(chromeOptions);
        } catch (WebDriverException e) {
            fail("WebDriver wasn`t created" + e.toString());
        }

        driver.manage().window().setSize(new Dimension(1440, 900));
    }

    @AfterMethod
    protected void ifTestFailedMakeScreenShot(ITestResult iTestResult) throws IOException {
        if (iTestResult.getStatus() == ITestResult.FAILURE) screenShot();
    }

    @AfterClass
    protected void tearDown() {
        driver.quit();
    }

    protected MainPage openWebApp() {
        driver.get(base_URL);
        return new MainPage(this);
    }

    public void log(String text) {
        logger.info(text);
    }

    public void error(String error) {
        logger.error(error);
    }

    public void screenShot() throws IOException {
        File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.txt'").format(new Date());
        File dest = new File("filePath/" + filename);
        FileUtils.copyFile(scr, dest);
    }


    private void turnOnImplicitWait() {
        try {
            driver.manage().timeouts().implicitlyWait(TIME_IMPLICIT_WAIT, TimeUnit.SECONDS);
        } catch (RuntimeException e) {
            fail("Elements was not loaded " + e.toString());
        }
    }

    private void turnOffImplicitWait() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public WebElement searchElement(By element) {
        WebElement foundElement = null;
        try {
            foundElement = driver.findElement(element);
        } catch (NoSuchElementException e) {
            fail("Element was not found " + e.toString());
        }
        return foundElement;
    }

    public WebElement waitTillElementIsVisible(WebElement element) {
        try {
            turnOffImplicitWait();
            WebDriverWait wait = new WebDriverWait(driver, TIME_EXPLICIT_WAIT);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            fail("Element not found " + e.toString());
        } catch (WebDriverException e) {
            fail("WebDriverWait was not created " + e.toString());
        } finally {
            turnOnImplicitWait();
        }
        return element;
    }

    public WebElement waitTillElementIsClickable(WebElement element) {
        try {
            turnOffImplicitWait();
            WebDriverWait wait = new WebDriverWait(driver, TIME_EXPLICIT_WAIT);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
        } catch (NoSuchElementException e) {
            fail("Element not found " + e.toString());
        } catch (WebDriverException e) {
            fail("WebDriverWait was not created " + e.toString());
        } finally {
            actions.moveToElement(element);
            turnOnImplicitWait();
        }
        return element;
    }

    public WebElement waitAndClickByElement(WebElement element) {
        waitTillElementIsClickable(element);
        element.click();
        return element;
    }


    public List<WebElement> waitTillAllElementsAreVisible(List<WebElement> listOfElements) {
        try {
            turnOffImplicitWait();
            WebDriverWait wait = new WebDriverWait(driver, TIME_EXPLICIT_WAIT);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(listOfElements));
        } catch (NoSuchElementException e) {
            fail("Elements were not found " + e.toString());
        } finally {
            turnOnImplicitWait();
        }
        return listOfElements;
    }
}


