package lowleveldesign.foodOrderingSystem.design;

import lowleveldesign.foodOrderingSystem.design.observable.OrderManager;
import lowleveldesign.foodOrderingSystem.design.observer.RestaurantManager;
import lowleveldesign.foodOrderingSystem.design.observer.TopRatedRestaurantByFoodManager;
import lowleveldesign.foodOrderingSystem.design.observer.TopRatedRestaurantManager;

import java.util.List;

public class Solution implements MyFoodOrderingSystem {
    private final OrderManager orderManager;
    private final TopRatedRestaurantByFoodManager topRatedRestaurantByFoodManager;
    private final TopRatedRestaurantManager topRatedRestaurantManager;

    public Solution() {
        RestaurantManager restaurantManager = new RestaurantManager();
        this.topRatedRestaurantManager = new TopRatedRestaurantManager(restaurantManager);
        this.topRatedRestaurantByFoodManager = new TopRatedRestaurantByFoodManager(restaurantManager);

        this.orderManager = new OrderManager();

        // restaurant manager should be in the first.
        // because first the Restaurant should have the rating
        // and the others can find the rating from the restaurant.
        this.orderManager.addOrderObservers(restaurantManager);
        this.orderManager.addOrderObservers(this.topRatedRestaurantManager);
        this.orderManager.addOrderObservers(this.topRatedRestaurantByFoodManager);

    }

    @Override
    public void orderFood(String orderId, String restaurantId, String foodItemId) {
        this.orderManager.createOrder(orderId, restaurantId, foodItemId);
    }

    @Override
    public void rateOrder(String orderId, int rating) {
        this.orderManager.updateOrder(orderId, rating);
    }

    @Override
    public List<String> getTopRestaurantsByFood(String foodItemId) {
        return this.topRatedRestaurantByFoodManager.getTopRestaurantsByFood(foodItemId);
    }

    @Override
    public List<String> getTopRatedRestaurants() {
        return this.topRatedRestaurantManager.getTopRatedRestaurants();
    }
}
