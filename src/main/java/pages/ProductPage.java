package pages;

import base.BaseTest;
import enums.Currency;
import enums.FilterForProduct;
import enums.SortByProducts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(css = "#products-orderby")
    private WebElement chooseSortBy;


    @FindBy(css = "#products-pagesize")
    private WebElement chooseDisplayPerPage;

    @FindBy(xpath = "//div[@class='product-grid']//span")
    private List<WebElement> listPricesOfProducts;

    @FindBy(xpath = "//div[@class='item-box']")
    private List<WebElement> listDisplayProduct;

    private String xpathForEnterOfText = "//a[contains(text(), '%s')]";
    private String xpathForClickByProduct = "//div[@class='item-grid']//div[@class='details']//a[contains(text(), '%s')]";


    public void setSort(SortByProducts sort) {
        chooseSortBy.click();
        String pathForDropListSortBy = "//select[@id='products-orderby']/option[contains(text(), '%s')]";
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(String.format(pathForDropListSortBy, sort.getSortName()))));
    }

    public void setDisplayPerPage(int amountDisplayProducts) {
        chooseDisplayPerPage.click();
        String pathForDropListDisplayPerPage = "//select[@id='products-pagesize']/option[contains(text(), '%d')]";
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(String.format(pathForDropListDisplayPerPage, amountDisplayProducts))));
    }

    public int getAmountDisplayProduct() {
        return listDisplayProduct.size();
    }

    public String getProductIsVisibleInDollar(Currency currentCurrency) {
        String getCurrency = "";
        List<String> currencyAllProduct = new ArrayList<>();
        for (int i = 0; i < listPricesOfProducts.size(); i++) {
            currencyAllProduct.add(listPricesOfProducts.get(i).getText().substring(0,1));
            if (currencyAllProduct.contains(currentCurrency.getCurrency())) getCurrency = String.valueOf(currencyAllProduct.get(i));
        }
        return getCurrency;
    }

    public void setFilters(FilterForProduct cpuType, FilterForProduct memory) {
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(String.format(xpathForEnterOfText, cpuType.getFilter()))));
        testClass.waitAndClickByElement(testClass.searchElement(By.xpath(String.format(xpathForEnterOfText, memory.getFilter()))));
    }

    public <T extends AbstractPage> T clickByProduct(String nameOfProduct, Class<T> createNewPage) {
        WebElement product = testClass.waitTillElementIsVisible(testClass.searchElement(By.xpath(String.format(xpathForClickByProduct, nameOfProduct))));
        product.click();
        return createPage(createNewPage);
    }




}

