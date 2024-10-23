package design_patterns.decorator.decorator;

import design_patterns.decorator.Coffee;

import java.util.ArrayList;
import java.util.List;

public class MochaDecorator extends AbstractCoffeeDecorator {
    public MochaDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public List<String> getIngredients() {
        ArrayList<String> strings = new ArrayList<>(super.getIngredients());
        strings.add("chocolate");
        return List.copyOf(strings);
    }

    @Override
    public int getPrice() {
        return 5 + super.getPrice();
    }

    @Override
    public String getName() {
        return "mocha";
    }
}
