package lowleveldesign.foodOrderingSystem.design.observer;

import lowleveldesign.foodOrderingSystem.design.dto.OrderItem;
import lowleveldesign.foodOrderingSystem.design.dto.Restaurant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RestaurantManager implements OrderObserver {
    public Map<String, Restaurant> restaurantMap = new HashMap<>();

    public void createOrder(OrderItem orderItem) {
        String restaurantId = orderItem.getRestaurantId();

        // getting the right restaurants
        Restaurant restaurant = restaurantMap.computeIfAbsent(restaurantId, x -> new Restaurant(restaurantId));
        restaurant.addOrder(orderItem);
    }


    public void updateOrder(OrderItem orderItem) {
        restaurantMap.get(orderItem.getRestaurantId()).updateOrder(orderItem);
    }

    public Map<String, Restaurant> getRestaurantMap() {
        return Collections.unmodifiableMap(this.restaurantMap);
    }
}
