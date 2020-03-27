package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BasePage extends AbstractPage {

    public BasePage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(xpath = "//div[@class='header-links-wrapper']//li[@id='topcartlink']")
    private WebElement shoppingCartButton;


    @FindBy(css = "#small-search-box-form > #small-searchterms")
    private WebElement searchProduct;

    public <T extends AbstractPage> T clickByNavigateButtonMenu(String categoryOfProducts, Class<T> createNewPage) {
        String pathForNavigationMenu = "//ul[@class='top-menu notmobile']//a[contains(text(), '%s')]";
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(String.format(pathForNavigationMenu, categoryOfProducts))));
        return createPage(createNewPage);
    }

    public <T extends AbstractPage> T clickByCart(Class<T> createNewPage) {
        testClass.waitAndClickByElement(shoppingCartButton);
        return createPage(createNewPage);
    }

    public <T extends AbstractPage> T searchProduct(String product, Class<T> createNewPage) {
        searchProduct.sendKeys(product + Keys.ENTER);
        return createPage(createNewPage);
    }
}
