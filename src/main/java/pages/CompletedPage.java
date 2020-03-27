package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileWriter;
import java.io.IOException;


public class CompletedPage extends BasePage {

    public CompletedPage(BaseTest testClass) {
        super(testClass);
    }

    @FindBy(css = ".order-number")
    private WebElement orderNumber;

    public void writeIntoFileOrderNumber() {
        try{
            String file = "src/main/resources/order_number.txt";

            FileWriter fileWrite = new FileWriter(file, false);

            fileWrite.write(orderNumber.getText());
            fileWrite.flush();
            fileWrite.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
