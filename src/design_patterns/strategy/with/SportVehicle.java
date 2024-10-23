package design_patterns.strategy.with;

import design_patterns.strategy.with.strategy.DriveStrategy;
import design_patterns.strategy.with.strategy.SportsDriveStrategy;

public class SportVehicle extends Vehicle {

    public SportVehicle() {
        super(new SportsDriveStrategy());
    }
}
