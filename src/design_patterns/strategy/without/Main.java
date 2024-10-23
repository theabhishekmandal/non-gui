package design_patterns.strategy.without;

import java.util.List;

/*
Without Strategy pattern common code is duplicated between two child siblings.
Because the common code is not present in the parent, so the code is duplicated.
 */
public class Main {
    public static void main(String[] args) {
        var listOfVehicles = List.of(new Vehicle(), new SportVehicle(), new OffRoadVehicle());

        for (Vehicle vehicle : listOfVehicles) {
            vehicle.drive();
        }

    }
}
