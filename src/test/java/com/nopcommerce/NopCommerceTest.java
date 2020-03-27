package com.nopcommerce;

import base.BaseTest;
import components.TermsPopupPage;
import enums.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class NopCommerceTest extends BaseTest {

    @Test
    public void chooseProductAndAddItsIntoCartTest() {

        MainPage mainPage = openWebApp();


        ComputersPage computersPage = mainPage.clickByNavigateButtonMenu("Computers", ComputersPage.class);
        assertEquals(computersPage.readCategoryProductFromPage(), readProductFromCategoryProduct(),
                "Category of Products arn`t visible or categories are wrong");


        ProductPage productPage = computersPage.clickByProductCategoryName(CategoryProduct.NOTEBOOKS, ProductPage.class);
        productPage.setSort(SortByProducts.PRICE_HIGH_TO_LOW);
        productPage.setDisplayPerPage(6);
        assertEquals(6, productPage.getAmountDisplayProduct(),
                "Amount visible of products arn`t match with chose sort");
        assertEquals(productPage.getProductIsVisibleInDollar(Currency.USD), Currency.USD.getCurrency(),
                "Currency has set on the products isn`t dollars");
        productPage.setFilters(FilterForProduct.CORE_I5, FilterForProduct.GB_4);


        InformationAboutProductPage informationAboutProductPage =
                productPage.clickByProduct("HP Spectre XT Pro UltraBook", InformationAboutProductPage.class);
        assertEquals(informationAboutProductPage.getAvailableProduct(), "Availability: In stock",
                "Availability isn`t stock or its contains another information");
        informationAboutProductPage.setAmountProductAndPushByAddToCart(2);
        informationAboutProductPage.clickByCart(CartPage.class);

    }

    @Test
    public void informationAboutAnOrderTest() {

        CartPage cartPage = addProductIntoCart();


        assertEquals(cartPage.getPageTitle(), "Shopping cart",
                "Shopping cart text isn`t visible on the Cart Page");
        assertEquals(cartPage.getAmountProductIntoCart(), 1,
                "Cart has more than 1 product or cart has products less than 1");
        cartPage.setGiftWrapping();
        assertEquals(cartPage.enterDiscountCodeOrGiftCardAndGetMessage(TextField.ENTER_COUPON, generateDiscountAndGiftCode(), Button.APPLY_COUPON),
                "The coupon code you entered couldn't be applied to your order",
                    "Error message isn`t visible or its message is wrong");
        assertEquals(cartPage.enterDiscountCodeOrGiftCardAndGetMessage(TextField.ENTER_GIFT_CARD, generateDiscountAndGiftCode(), Button.ADD_GIFT_CART),
                "The coupon code you entered couldn't be applied to your order",
                "Error message isn`t visible or its message is wrong");
        cartPage.setEstimateShipping("United States", "New York", generateZipCode(), Button.ESTIMATE_SHIPPING);
        assertEquals(cartPage.getTotalFieldAndGetGiftWrapping(1,"Total"), cartPage.getPriceFromAllTotal(),
                "Prices arn`t visible or incorrect");


        TermsPopupPage termsPopupPage = cartPage.clickAndOpenTermsPopupPage(Button.READ_TERMS, TermsPopupPage.class);
        assertEquals(termsPopupPage.getTextFromConditionsOfUse(), "Put your conditions of use information here. You can edit this in the admin site.",
                "Text isn`t visible on Popup Pge or this text is wrong");
        termsPopupPage.closeTermsPopupPage();


        SignInPage signInPage = cartPage.clickByAgreeWithTermsAndClickByCheckout(Button.CHECKOUT, SignInPage.class);
        signInPage.clickByCheckoutAsGuest(CheckoutPage.class);
    }


    @Test
    public void informationAboutCustomerAndMakeAnOrderTest() {

        CheckoutPage checkoutPage = makeAnOrder();


        assertEquals(checkoutPage.getCheckoutText(), "Checkout",
                "Checkout text isn`t visible or wrong on this page");
        checkoutPage.setCheckBoxShipToTheSameAddress();
        checkoutPage.setFirstLastNameAndEmail();
        checkoutPage.setCountryCityAddress();
        checkoutPage.setZipCodePhoneNumberAndClickByButtonContinue(generateZipCode());
        checkoutPage.setShippingMethod(ShippingAndPayment.NEXT_DAY_AIR);
        checkoutPage.setPaymentMethod(ShippingAndPayment.CREDIT_CARD);
        checkoutPage.setPaymentInformation();


        CompletedPage completedPage = checkoutPage.clickByConfirm(CompletedPage.class);
        completedPage.writeIntoFileOrderNumber();
    }


    private CartPage addProductIntoCart() {
        MainPage mainPage = openWebApp();

        ProductPage productPage = mainPage.searchProduct("HP Spectre XT Pro UltraBook", ProductPage.class);

        InformationAboutProductPage informationAboutProductPage = productPage.clickByProduct("HP Spectre XT Pro UltraBook", InformationAboutProductPage.class);
        informationAboutProductPage.setAmountProductAndPushByAddToCart(2);
        return informationAboutProductPage.clickByCart(CartPage.class);
    }

  private CheckoutPage makeAnOrder() {
      CartPage cartPage = addProductIntoCart();
      cartPage.setGiftWrapping();

      SignInPage signInPage = cartPage.clickByAgreeWithTermsAndClickByCheckout(Button.CHECKOUT, SignInPage.class);
      return signInPage.clickByCheckoutAsGuest(CheckoutPage.class);

  }

    private List<String> readProductFromCategoryProduct() {
        List<String> enumsList = new ArrayList<>();
        for (CategoryProduct products : CategoryProduct.values()) {
            enumsList.add(products.getCategoryProduct());
        }
        return enumsList;
    }


    private String generateDiscountAndGiftCode() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    private String generateZipCode() {
        return RandomStringUtils.randomNumeric(5);
    }

}
