package design_patterns.strategy.without;

public class OffRoadVehicle extends Vehicle {

    // code is duplicated here
    @Override
    public void drive() {
        System.out.println("sports drive capability");
    }
}
