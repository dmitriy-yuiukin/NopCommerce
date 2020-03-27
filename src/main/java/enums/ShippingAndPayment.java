package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShippingAndPayment {

    GROUND("#shippingoption_0"),
    NEXT_DAY_AIR("#shippingoption_1"),
    SECOND_DAY_AIR("#shippingoption_2"),
    MONEY_ORDER("#paymentmethod_0"),
    CREDIT_CARD("#paymentmethod_1");

    private final String shippingPayment;

}
