package design_patterns.observer;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyAllObservers(Object obj);
}
