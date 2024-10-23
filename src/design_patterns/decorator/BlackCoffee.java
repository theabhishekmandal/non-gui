package design_patterns.decorator;

import java.util.List;

public class BlackCoffee implements Coffee {


    @Override
    public List<String> getIngredients() {
        return List.of("coffee", "hot water");
    }

    @Override
    public int getPrice() {
        return 5;
    }

    @Override
    public String getName() {
        return "black coffee";
    }
}
