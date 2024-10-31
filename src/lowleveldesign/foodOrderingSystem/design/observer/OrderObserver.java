package lowleveldesign.foodOrderingSystem.design.observer;

import lowleveldesign.foodOrderingSystem.design.dto.OrderItem;

public interface OrderObserver {
    void createOrder(OrderItem orderItem);
    void updateOrder(OrderItem orderItem);
}
