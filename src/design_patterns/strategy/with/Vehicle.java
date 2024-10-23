package design_patterns.strategy.with;

import design_patterns.strategy.with.strategy.DriveStrategy;

public class Vehicle {
    protected final DriveStrategy driveStrategy;

    public Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        driveStrategy.drive();
    }
}
