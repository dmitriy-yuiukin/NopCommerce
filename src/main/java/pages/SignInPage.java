package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    public SignInPage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(xpath = "//input[@class='button-1 checkout-as-guest-button']")
    private WebElement checkoutASGuestButton;

    @FindBy(xpath = "//input[@class='button-1 register-button']")
    private WebElement registerButton;

    @FindBy(xpath = "//input[@class='button-1 login-button']")
    private WebElement loginButton;

    public <T extends AbstractPage> T clickByCheckoutAsGuest(Class<T> createNewPage) {
        checkoutASGuestButton.click();
        return createPage(createNewPage);
    }


}
