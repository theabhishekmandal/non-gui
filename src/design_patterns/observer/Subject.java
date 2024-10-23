package design_patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject implements Observable {
    // one to many relationShips
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers(Object object) {
        for (Observer observer : observers) {
            observer.update(String.valueOf(object));
        }
    }
}

