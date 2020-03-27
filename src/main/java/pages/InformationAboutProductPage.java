package pages;

import base.BaseTest;
import enums.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InformationAboutProductPage extends BasePage {

    public InformationAboutProductPage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(css = "span[class='cart-qty']")
    private WebElement shoppingCartButton;

    @FindBy(css = "#stock-availability-value-7")
    private WebElement availableProduct;

    @FindBy(css = "#addtocart_7_EnteredQuantity")
    private WebElement setAmountProduct;


    public String getAvailableProduct() {
        return String.format("Availability: %s", availableProduct.getText());
    }

    public void setAmountProductAndPushByAddToCart(int amountOfProduct) {
        if (shoppingCartButton.getText().equals("(0)")) {
            setAmountProduct.clear();
            setAmountProduct.sendKeys(String.valueOf(amountOfProduct));
            testClass.waitAndClickByElement(testClass.searchElement(By.cssSelector(Button.ADD_TO_CART.getButton())));
        }
    }

}
