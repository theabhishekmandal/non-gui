package design_patterns.decorator;

import java.util.List;

public interface Coffee {
    List<String> getIngredients();

    int getPrice();

    String getName();
}
