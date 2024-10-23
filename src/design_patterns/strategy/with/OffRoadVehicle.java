package design_patterns.strategy.with;

import design_patterns.strategy.with.strategy.DriveStrategy;
import design_patterns.strategy.with.strategy.SportsDriveStrategy;

public class OffRoadVehicle extends Vehicle {

    public OffRoadVehicle() {
        super(new SportsDriveStrategy());
    }
}
