package components;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractPopupPage extends AbstractComponent {

    public AbstractPopupPage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(xpath = "//button[@class='ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-close']")
    private WebElement closePopupPageButton;

    public void closeTermsPopupPage() {
        closePopupPageButton.click();
    }
}
