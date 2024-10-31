package lowleveldesign.foodOrderingSystem.design.dto;

public class OrderItem {
    private final String orderId;
    private final String foodItemId;
    private final String restaurantId;
    private int rating;

    public OrderItem(String orderId, String foodItemId, String restaurantId) {
        this.orderId = orderId;
        this.foodItemId = foodItemId;
        this.restaurantId = restaurantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getFoodItemId() {
        return foodItemId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
