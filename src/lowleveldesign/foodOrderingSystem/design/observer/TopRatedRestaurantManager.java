package lowleveldesign.foodOrderingSystem.design.observer;

import lowleveldesign.foodOrderingSystem.design.dto.OrderItem;
import lowleveldesign.foodOrderingSystem.design.dto.Restaurant;

import java.util.*;

public class TopRatedRestaurantManager implements OrderObserver {
    private final RestaurantManager restaurantManager;
    private final Set<Restaurant> topRatedRestaurants;
    private static final int TOP_RESTAURANT_SIZE = 20;

    public TopRatedRestaurantManager(RestaurantManager restaurantManager) {
        this.restaurantManager = restaurantManager;
        this.topRatedRestaurants = new TreeSet<>(Comparator.comparing(Restaurant::getRating,
                Comparator.reverseOrder()).thenComparing(Restaurant::getRestaurantId)
        );
    }

    @Override
    public void createOrder(OrderItem orderItem) {
        // do nothing
    }

    @Override
    public void updateOrder(OrderItem orderItem) {

        Map<String, Restaurant> restaurantMap = restaurantManager.getRestaurantMap();

        Restaurant restaurant = restaurantMap.get(orderItem.getRestaurantId());
        // since it is mutable we are removing and adding again
        topRatedRestaurants.remove(restaurant);
        topRatedRestaurants.add(restaurant);
    }

    public List<String> getTopRatedRestaurants() {
        return this.topRatedRestaurants.stream()
                .limit(TOP_RESTAURANT_SIZE)
                .map(Restaurant::getRestaurantId).toList();
    }
}
