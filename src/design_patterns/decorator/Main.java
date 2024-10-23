package design_patterns.decorator;

import design_patterns.decorator.decorator.CappuccinoDecorator;
import design_patterns.decorator.decorator.MochaDecorator;

/**
 * Decorator design pattern, without inheriting the main class, implementing both has-a and is-a relationship.
 */
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new BlackCoffee();
        Coffee cappuccino = new CappuccinoDecorator(coffee);
        Coffee mocha = new MochaDecorator(cappuccino);

        printAboutCoffee(coffee);
        printAboutCoffee(cappuccino);
        printAboutCoffee(mocha);
    }

    static void printAboutCoffee(Coffee coffee) {
        System.out.println("coffee name = " + coffee.getName() + "\ningredients = " + coffee.getIngredients()
                + "\nprice =  " + coffee.getPrice() + "\n");
    }
}
