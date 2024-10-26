package design_patterns.factory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Coffee cappuccino = CoffeeFactory.createCoffee(CoffeeType.cappuccino);
        Coffee blackCoffee = CoffeeFactory.createCoffee(CoffeeType.blackCoffee);
        Coffee mocha = CoffeeFactory.createCoffee(CoffeeType.mocha);

        List.of(cappuccino, blackCoffee, mocha).forEach(
                x -> System.out.println(x.getName())
        );
    }
}

interface Coffee {
    String getName();
}

class BlackCoffee implements Coffee {

    @Override
    public String getName() {
        return "black coffee";
    }
}


class Cappuccino implements Coffee {

    @Override
    public String getName() {
        return "cappuccino";
    }
}

class Mocha implements Coffee {

    @Override
    public String getName() {
        return "mocha";
    }
}

enum CoffeeType {
    blackCoffee, cappuccino, mocha
}

class CoffeeFactory {

    public static Coffee createCoffee(CoffeeType coffeeType) {
        return switch (coffeeType) {
            case blackCoffee -> new BlackCoffee();
            case cappuccino -> new Cappuccino();
            case mocha -> new Mocha();
            case null, default -> new Coffee() {

                @Override
                public String getName() {
                    return "empty coffee";
                }
            };
        };
    }
}