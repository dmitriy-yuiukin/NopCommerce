package pages;

import base.BaseTest;
import constans.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;


public abstract class AbstractPage {

    protected BaseTest testClass;

    public AbstractPage(BaseTest testClass) {
        this.testClass = testClass;
        testClass.log(String.format("Initialize: >>>%s<<<", this.getClass().getSimpleName()));
        waitForLoad();
        PageFactory.initElements(testClass.getDriver(), this);
    }

    public void waitForLoad() {
        new WebDriverWait(testClass.getDriver(), Wait.TIME_IMPLICIT_WAIT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    protected <T extends AbstractPage> T createPage(Class<T> clazz) {
        return createPage(clazz, null);
    }

    protected <T extends AbstractPage> T createPage(Class<T> clazz, String param) {
        if (param != null)
            try {
                return clazz.getConstructor(BaseTest.class, String.class).newInstance(testClass, param);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException("Cannot create object of class: " + clazz.getName() + ", with param: " + param);
            }
        else {
            try {
                return clazz.getConstructor(BaseTest.class).newInstance(testClass);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Cannot create object of class: " + clazz.getName());
            }
        }
    }
}
