package pages;

import base.BaseTest;
import enums.CategoryProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ComputersPage extends BasePage {

    public ComputersPage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(css = ".item-box")
    private List<WebElement> listOfProducts;


    public List<String> readCategoryProductFromPage() {
        List<String> productsName = new ArrayList<>();
        for (WebElement webElement : listOfProducts) {
            productsName.add(webElement.getText());
        }
        return productsName;
    }

    public <T extends AbstractPage> T clickByProductCategoryName(CategoryProduct categoryOfProduct, Class<T> createNewPage) {
        String pathForCategoryName = "//div[@class='item-box']//h2//a[contains(text(), '%s')]";
        WebElement product = testClass.waitTillElementIsVisible(testClass.searchElement(By.xpath(String.format(pathForCategoryName, categoryOfProduct.getCategoryProduct()))));
        product.click();
        return createPage(createNewPage);
    }



}

