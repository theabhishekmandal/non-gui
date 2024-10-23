package design_patterns.strategy.with.strategy;

public class SportsDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("sports drive capability");
    }
}
