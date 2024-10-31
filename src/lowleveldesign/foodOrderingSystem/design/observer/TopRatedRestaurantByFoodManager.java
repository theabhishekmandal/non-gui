package lowleveldesign.foodOrderingSystem.design.observer;

import lowleveldesign.foodOrderingSystem.design.dto.OrderItem;
import lowleveldesign.foodOrderingSystem.design.dto.Restaurant;

import java.util.*;

public class TopRatedRestaurantByFoodManager implements OrderObserver {
    private final RestaurantManager restaurantManager;
    private final Map<String, Set<FoodRatingPerRestaurant>> foodIdToRestaurantMap = new TreeMap<>();
    private final Comparator<FoodRatingPerRestaurant> comparator;
    private static final int TOP_RESTAURANT_SIZE = 20;

    public TopRatedRestaurantByFoodManager(RestaurantManager restaurantManager) {
        this.restaurantManager = restaurantManager;
        this.comparator = Comparator.comparing(FoodRatingPerRestaurant::getFoodRating, Comparator.reverseOrder())
                .thenComparing(FoodRatingPerRestaurant::getRestaurantId);
    }

    @Override
    public void createOrder(OrderItem orderItem) {
        // not needed.
    }

    @Override
    public void updateOrder(OrderItem orderItem) {
        Map<String, Restaurant> restaurantMap = restaurantManager.getRestaurantMap();

        Restaurant restaurant = restaurantMap.get(orderItem.getRestaurantId());
        String foodItemId = orderItem.getFoodItemId();

        FoodRatingPerRestaurant e = new FoodRatingPerRestaurant(foodItemId, restaurant);
        Set<FoodRatingPerRestaurant> foodRatingPerRestaurants = foodIdToRestaurantMap
//                 TreeSet does not uses equals and hashcode
                .computeIfAbsent(foodItemId, x -> new TreeSet<>(comparator));
        foodRatingPerRestaurants.add(e);
    }

    public List<String> getTopRestaurantsByFood(String foodItemId) {
        return this.foodIdToRestaurantMap.get(foodItemId).stream().limit(TOP_RESTAURANT_SIZE)
                .peek(x -> System.out.println("res id = " + x.getRestaurantId() + " rating = " + x.getFoodRating()))
                .sorted(comparator)
                .map(FoodRatingPerRestaurant::getRestaurantId).toList();
    }


    static class FoodRatingPerRestaurant {
        private final String foodItemId;
        private final Restaurant restaurant;

        FoodRatingPerRestaurant(String foodItemId, Restaurant restaurant) {
            this.foodItemId = foodItemId;
            this.restaurant = restaurant;
        }

        double getFoodRating() {
            return restaurant.getFoodItemMap().get(this.foodItemId).getRating();
        }

        String getRestaurantId() {
            return restaurant.getRestaurantId();
        }

        String getFoodItemId() {
            return this.foodItemId;
        }
    }
}
