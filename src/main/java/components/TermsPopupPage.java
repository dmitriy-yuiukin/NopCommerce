package components;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TermsPopupPage extends AbstractPopupPage {

    public TermsPopupPage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(css = "#ph-topic > .page-body")
    private WebElement getTextFromConditions;


    public String getTextFromConditionsOfUse() {
        return getTextFromConditions.getText();
    }


}
