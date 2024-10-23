package design_patterns.decorator.decorator;

import design_patterns.decorator.Coffee;

import java.util.List;

public abstract class AbstractCoffeeDecorator implements Coffee {
    protected final Coffee coffee;

    public AbstractCoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public List<String> getIngredients() {
        return coffee.getIngredients();
    }

    @Override
    public int getPrice() {
        return coffee.getPrice();
    }
}
