package lowleveldesign.foodOrderingSystem.design.observable;

import lowleveldesign.foodOrderingSystem.design.observer.OrderObserver;
import lowleveldesign.foodOrderingSystem.design.dto.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManager implements OrderObservable {

    private final List<OrderObserver> orderObserverList;
    private final Map<String, OrderItem> orderItemMap = new HashMap<>();

    public OrderManager() {
        this.orderObserverList = new ArrayList<>();
    }

    public OrderItem createOrder(String orderId, String restaurantId, String foodItemId) {
        OrderItem orderItem = new OrderItem(orderId, foodItemId, restaurantId);
        orderItemMap.put(orderId, orderItem);
        notifyOrderCreation(orderItem);
        return orderItem;
    }

    public OrderItem updateOrder(String orderId, int rating) {
        OrderItem orderItem = orderItemMap.get(orderId);
        if (orderItem != null) {
            orderItem.setRating(rating);
            notifyOrderUpdation(orderItem);
        }
        return  orderItem;
    }


    @Override
    public void addOrderObservers(OrderObserver orderObserver) {
        orderObserverList.add(orderObserver);
    }

    @Override
    public void notifyOrderCreation(OrderItem orderItem) {
        for (OrderObserver orderObserver : orderObserverList) {
            orderObserver.createOrder(orderItem);
        }
    }

    @Override
    public void notifyOrderUpdation(OrderItem orderItem) {
        for (OrderObserver orderObserver : orderObserverList) {
            orderObserver.updateOrder(orderItem);
        }
    }
}
