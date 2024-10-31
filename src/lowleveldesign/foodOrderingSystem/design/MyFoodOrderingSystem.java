package lowleveldesign.foodOrderingSystem.design;

import java.util.List;

public interface MyFoodOrderingSystem {
    void orderFood(String orderId, String restaurantId, String foodItemId);
    void rateOrder(String orderId, int rating);
    List<String> getTopRestaurantsByFood(String foodItemId);
    List<String> getTopRatedRestaurants();
}
/*
Solution
    -   void orderFood(String orderId, String restaurantId, String foodItemId)
        -   create Order
        -   pass it to Restaurant
            -   create Restaurant if not exist
            -   pass order to Restaurant
            -   pass foodItem present in order to Restaurant

            -   Restaurant now has orderId and FoodItemId

    -   void rateOrder(String orderId, int rating);
        -   get the order using orderId
        -   add the rating
        -   pass order to the Restaurant that served the order

        -   Restaurant now has
            -   the rating of the food
            -   the overall rating of restaurant

    -   List<String> getTopRestaurantsByFood(String foodItemId)
        -   search FoodItemId served in which restaurants
        -   get rating of the food in descending order


    -   List<String> getTopRatedRestaurants()
        -   get top RatedRestaurants in descending order


Data Models
    Order
        -   String orderId;
        -   String foodItemId;
        -   String restaurantId;
        -   int rating;
        -   methods
            -   addRating(int n);

    FoodItem
        -   String id;
        -   int rating;

    Restaurant
        -   String id;
        -   Map<String, OrderItem> orderIdToOrderMap;
        -   Map<String, FoodItem> foodItemIdToOrderMap;

        -   methods
            -   addOrder(OrderItem orderItem)
                -   adds order
                -   adds foodItem

            -   rateOrder(OrderItem orderItem)
                -   rates order
                -   rates foodItem
                -   rates average Rating of restaurant


    SolutionClass
        -    void orderFood(String orderId, String restaurantId, String foodItemId)
            -   create OrderItem
            -   pass it to RestaurantManager


 */