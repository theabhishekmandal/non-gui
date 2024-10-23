package design_patterns.strategy.with;

import java.util.List;

/*
With Strategy pattern, common code is extracted between the siblings.
 */
public class Main {
    public static void main(String[] args) {
        var listOfVehicles = List.of(new NormalVehicle(), new SportVehicle(), new OffRoadVehicle());

        for (Vehicle vehicle : listOfVehicles) {
            vehicle.drive();
        }

    }
}
