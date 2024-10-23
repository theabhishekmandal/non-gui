package design_patterns.observer;

import java.util.List;



public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();

        Observer observer1 = new ConcreteObserver("Observer 1");
        Observer observer2 = new ConcreteObserver("Observer 2");

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        subject.notifyAllObservers("something has changed");

        System.out.println("removing observer2");
        subject.removeObserver(observer2);

        subject.notifyAllObservers("changing again");
    }
}
