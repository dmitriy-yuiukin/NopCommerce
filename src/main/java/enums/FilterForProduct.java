package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FilterForProduct {

    CORE_I5("Intel Core i5"),
    CORE_I7("Intel Core i7"),
    GB_4("4 GB"),
    GB_8("8 GB"),
    GB_16("16 GB");

    private final String filter;

}
