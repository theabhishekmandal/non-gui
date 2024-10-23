package design_patterns.strategy.with;

import design_patterns.strategy.with.strategy.NormalDriveStrategy;

public class NormalVehicle extends Vehicle {
    public NormalVehicle(){
        super(new NormalDriveStrategy());
    }
}
