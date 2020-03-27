package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractTablePage extends BasePage {

    public AbstractTablePage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(xpath = "//table[@class='cart']//tbody//tr")
    private List<WebElement> listProducts;

    public int getAmountProductIntoCart() {
        if (listProducts.size() > 0)
            return listProducts.size();
        else
            throw new RuntimeException("List is empty");
    }

    private List<WebElement> getHeadings() {
        return testClass.waitTillAllElementsAreVisible(testClass.getDriver().findElements(By.xpath("//table[@class='cart']//th")));
    }

    private List<List<WebElement>> getRowsAndColumns() {
        List<List<WebElement>> columns = new ArrayList<>();
        for (WebElement listProduct : listProducts) {
            columns.add(listProduct.findElements(By.xpath(".//td")));
        }
        return columns;
    }

    public List<Map<String, WebElement>> getRowsAndColumnsByHeading() {
        List<Map<String, WebElement>> rowsAndColumns = new ArrayList<>();
        Map<String, WebElement> headingColumn = new HashMap<>();
        for (List<WebElement> row : getRowsAndColumns()) {
            for (int i = 0; i < getHeadings().size(); i++) {
                headingColumn.put(getHeadings().get(i).getText(), row.get(i));
            }
            rowsAndColumns.add(headingColumn);
        }
        return rowsAndColumns;
    }
}
