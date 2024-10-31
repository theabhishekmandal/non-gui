package lowleveldesign.foodOrderingSystem.design.observable;

import lowleveldesign.foodOrderingSystem.design.observer.OrderObserver;
import lowleveldesign.foodOrderingSystem.design.dto.OrderItem;

public interface OrderObservable {

    void addOrderObservers(OrderObserver orderObserver);

    void notifyOrderCreation(OrderItem orderItem);

    void notifyOrderUpdation(OrderItem orderItem);
}
