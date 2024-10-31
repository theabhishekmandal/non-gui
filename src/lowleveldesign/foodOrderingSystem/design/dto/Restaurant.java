package lowleveldesign.foodOrderingSystem.design.dto;

import java.util.*;

public class Restaurant {
    private final String id;
    private final Map<String, OrderItem> orderItemMap = new HashMap<>();
    private final Map<String, Rating> foodItemIds = new TreeMap<>();
    private final Rating rating = new Rating();

    public Restaurant(String id) {
        this.id = id;
    }

    public void addOrder(OrderItem orderItem) {
        String foodItemId = orderItem.getFoodItemId();
        String orderId = orderItem.getOrderId();

        OrderItem orderItem1 = new OrderItem(orderId, foodItemId, this.id);
        orderItemMap.put(orderId, orderItem1);
        foodItemIds.putIfAbsent(foodItemId, new Rating());
    }

    // no wrong restaurant order should be received.
    public void updateOrder(OrderItem orderItem) {
        int rating = orderItem.getRating();
        this.orderItemMap.get(orderItem.getOrderId()).setRating(rating);
        this.foodItemIds.get(orderItem.getFoodItemId()).addRating(rating);
        this.rating.addRating(rating);
    }

    public double getRating() {
        return this.rating.getRating();
    }


    public Map<String, Rating> getFoodItemMap() {
        return Collections.unmodifiableMap(this.foodItemIds);
    }

    public Map<String, OrderItem> getOrderItemMap() {
        return Collections.unmodifiableMap(this.orderItemMap);
    }

    public String getRestaurantId() {
        return this.id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Restaurant that = (Restaurant) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
