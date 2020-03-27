package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryProduct {

    DESKTOPS("Desktops"),
    NOTEBOOKS("Notebooks"),
    SOFTWARE("Software");

    private final String categoryProduct;

}
