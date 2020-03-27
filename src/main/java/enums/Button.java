package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Button {

    ADD_TO_CART("#add-to-cart-button-7"),
    GO_TO_CART(".buttons > input[class='button-1 cart-button']"),
    ADD_TO_WISHLIST("#add-to-wishlist-button-7"),
    ADD_TO_COMPARE_LIST(".button-2 add-to-compare-list-button"),
    EMAIL_A_FRIEND(".button-2 email-a-friend-button"),
    UPDATE_SHOPPING_CART(".button-2 update-cart-button"),
    CONTINUE_SHOPPING(".button-2 continue-shopping-button"),
    APPLY_COUPON(".coupon-code > input[id='applydiscountcouponcode']"),
    ADD_GIFT_CART(".coupon-code > input[id='applygiftcardcouponcode']"),
    ESTIMATE_SHIPPING(".buttons > input[id='estimate-shipping-button']"),
    CHECKOUT(".checkout-buttons > #checkout"),
    READ_TERMS(".terms-of-service > #read-terms"),
    CONTINUE("//input[@value='Continue']"),
    CONFIRM("//input[@value='Confirm']"),
    BACK("//div[@id='shipping-method-buttons-container']//a");


    private final String button;


}
