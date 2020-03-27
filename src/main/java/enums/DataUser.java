package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DataUser {

    FIRST_NAME("Jonh"),
    LAST_NAME("Dantenevich"),
    EMAIL("dantenevich@gmail.com"),
    COUNTRY("United Kingdom"),
    CITY("London"),
    ADDRESS("36 Chapel St"),
    PHONE_NUMBER("020 7946 0240"),
    CREDIT_CARD("Master Card"),
    CARD_HOLDER("Jonh Dantenevich"),
    NUMBER_OF_CARD("5872 5838 3924 9041"),
    EXPIRE_MONTH("03"),
    EXPIRE_YEAR("2023"),
    CV_CODE("123");

   private final String value;



}
