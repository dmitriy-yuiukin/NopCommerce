package pages;

import base.BaseTest;
import enums.Button;
import enums.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CartPage extends AbstractTablePage {

    public CartPage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(css = ".page-title")
    private WebElement pageTitle;

    @FindBy(css = "select[id='checkout_attribute_1']")
    private WebElement chooseGiftWrapping;

    @FindBy(css = "#CountryId")
    private WebElement setCountry;

    @FindBy(css = ".state-input")
    private WebElement setState;

    @FindBy(css = ".terms-of-service > #termsofservice")
    private WebElement agreeCheckBox;


    public String getPageTitle() {
        return pageTitle.getText();
    }


    public String setGiftWrapping() {
        chooseGiftWrapping.click();
        return testClass.waitAndClickByElement(testClass.searchElement(By.cssSelector("#checkout_attribute_1 option:nth-of-type(2)"))).getText();
    }

    public String enterDiscountCodeOrGiftCardAndGetMessage(TextField chooseFieldFoEnter, String discountAndGiftCode, Button clickButton) {
        testClass.waitTillElementIsVisible(testClass.searchElement(By.cssSelector(chooseFieldFoEnter.getPathForEnterIntoTextFiled()))).sendKeys(discountAndGiftCode);
        testClass.waitAndClickByElement(testClass.searchElement(By.cssSelector(clickButton.getButton())));
        return testClass.waitTillElementIsVisible(testClass.searchElement(By.cssSelector(".message-failure"))).getText();
    }

    public void setEstimateShipping(String country, String state, String zipCode, Button clickByButton) {
        setCountry.sendKeys(country);
        setState.sendKeys(state);
        testClass.waitTillElementIsVisible(testClass.searchElement(By.cssSelector(TextField.ENTER_ZIP_CODE.getPathForEnterIntoTextFiled()))).sendKeys(zipCode);
        testClass.waitAndClickByElement(testClass.searchElement(By.cssSelector(clickByButton.getButton())));
    }

    public Double getTotalFieldAndGetGiftWrapping(int numberOfProduct, String nameOfHeading) {
        Double getPriceOfGiftWrapping = Double.parseDouble(setGiftWrapping().replaceAll("[^10.]", ""));
        Double getValueFromTable =
                Double.parseDouble(getRowsAndColumnsByHeading().get(numberOfProduct - 1).get(nameOfHeading).getText().replaceAll("[$,]", ""));
        return (getPriceOfGiftWrapping + getValueFromTable);
    }

    public Double getPriceFromAllTotal() {
        return Double.parseDouble(testClass.searchElement(By.xpath("//table[@class='cart-total']//tr[@class='order-total']//span")).getText().replaceAll("[$,]", ""));
    }

    public <T extends AbstractPage> T clickAndOpenTermsPopupPage(Button clickReadTerms, Class<T> createNewPopupPage) {
        testClass.waitAndClickByElement(testClass.searchElement(By.cssSelector(clickReadTerms.getButton())));
        return createPage(createNewPopupPage);
    }

    public <T extends AbstractPage> T clickByAgreeWithTermsAndClickByCheckout(Button clickCheckOutButton, Class<T> createNewPage) {
        agreeCheckBox.click();
        testClass.waitAndClickByElement(testClass.searchElement(By.cssSelector(clickCheckOutButton.getButton())));
        return createPage(createNewPage);
    }

}
