package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TextField {

    ENTER_COUPON(".discount-coupon-code"),
    ENTER_GIFT_CARD(".gift-card-coupon-code"),
    ENTER_ZIP_CODE(".zip-input");

    private final String pathForEnterIntoTextFiled;

}
