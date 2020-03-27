package pages;

import base.BaseTest;
import enums.Button;
import enums.DataUser;
import enums.ShippingAndPayment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    public CheckoutPage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(css = ".page-title")
    private WebElement getPageTitle;

    @FindBy(css = "#ShipToSameAddress")
    private WebElement checkBoxShipToTheSameAddress;

    @FindBy(css = "#BillingNewAddress_FirstName")
    private WebElement setFirstName;

    @FindBy(css = "#BillingNewAddress_LastName")
    private WebElement setLastName;

    @FindBy(css = "#BillingNewAddress_Email")
    private WebElement setEmail;

    @FindBy(css = "#BillingNewAddress_CountryId")
    private WebElement setCountry;

    @FindBy(css = "#BillingNewAddress_City")
    private WebElement setCity;

    @FindBy(css = "#BillingNewAddress_Address1")
    private WebElement setAddress;

    @FindBy(css = "#BillingNewAddress_ZipPostalCode")
    private WebElement setZipCode;

    @FindBy(css = "#BillingNewAddress_PhoneNumber")
    private WebElement setPhoneNumber;

    @FindBy(css = "#CreditCardType")
    private WebElement setCreditCard;

    @FindBy(css = "#ExpireMonth")
    private WebElement setExpireMonth;

    @FindBy(css = "#ExpireYear")
    private WebElement setExpireYear;

    private final String XPATH_SHIPPING_METHOD = "//li[@id='opc-shipping_method']";
    private final String XPATH_PAYMENT_METHOD = "//li[@id='opc-payment_method']";
    private final String XPATH_PAYMENT_INFORMATION = "//li[@id='opc-payment_info']";


    public String getCheckoutText() {
        return getPageTitle.getText();
    }

    public void setCheckBoxShipToTheSameAddress() {
        if(checkBoxShipToTheSameAddress.getAttribute("checked") == null)
            checkBoxShipToTheSameAddress.click();
    }

    public void setFirstLastNameAndEmail() {
        setFirstName.sendKeys(DataUser.FIRST_NAME.getValue());
        setLastName.sendKeys(DataUser.LAST_NAME.getValue());
        setEmail.sendKeys(DataUser.EMAIL.getValue());
    }

    public void setCountryCityAddress() {
        setCountry.sendKeys(DataUser.COUNTRY.getValue());
        setCity.sendKeys(DataUser.CITY.getValue());
        setAddress.sendKeys(DataUser.ADDRESS.getValue());
    }


    public void setZipCodePhoneNumberAndClickByButtonContinue(String zipCode) {
        setZipCode.sendKeys(zipCode);
        setPhoneNumber.sendKeys(DataUser.PHONE_NUMBER.getValue());
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(Button.CONTINUE.getButton())));
    }

    public void setShippingMethod(ShippingAndPayment shippingAndPaymentMethodMethod) {
        testClass.waitAndClickByElement(testClass.searchElement(By.cssSelector(shippingAndPaymentMethodMethod.getShippingPayment())));
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(XPATH_SHIPPING_METHOD + Button.CONTINUE.getButton())));
    }

    public void setPaymentMethod(ShippingAndPayment shippingAndPaymentMethodMethod) {
        testClass.waitAndClickByElement(testClass.searchElement(By.cssSelector(shippingAndPaymentMethodMethod.getShippingPayment())));
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(XPATH_PAYMENT_METHOD + Button.CONTINUE.getButton())));
    }

    public void setPaymentInformation() {
        setCreditCard.sendKeys(DataUser.CREDIT_CARD.getValue());
        testClass.waitTillElementIsVisible(testClass.searchElement(By.cssSelector("#CardholderName"))).sendKeys(DataUser.CARD_HOLDER.getValue());
        testClass.waitTillElementIsVisible(testClass.searchElement(By.cssSelector("#CardNumber"))).sendKeys(DataUser.NUMBER_OF_CARD.getValue());
        setExpireMonth.sendKeys(DataUser.EXPIRE_MONTH.getValue());
        setExpireYear.sendKeys(DataUser.EXPIRE_YEAR.getValue());
        testClass.waitTillElementIsVisible(testClass.searchElement(By.cssSelector("#CardCode"))).sendKeys(DataUser.CV_CODE.getValue());
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(XPATH_PAYMENT_INFORMATION + Button.CONTINUE.getButton())));
    }


    public <T extends AbstractPage> T clickByConfirm(Class<T> createNewPage) {
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(Button.CONFIRM.getButton())));
        return createPage(createNewPage);
    }




}
