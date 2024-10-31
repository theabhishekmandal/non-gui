package lowleveldesign.foodOrderingSystem.design;

public class Main {
    public static void main(String[] args) {
        MyFoodOrderingSystem foodOrderingSystem = new Solution();
        /*
        food=0
         Restaurant0
            -   3, = 3
         Restaurnat1
            -   3, 4, 2, 4 = 13 / 4 = 3.25
         Restaurant2
            -   1, 5 = 6/ 2 = 3

            1, 0, 2
         */

        foodOrderingSystem.orderFood("order-0", "restaurant-0", "food-1");
        foodOrderingSystem.rateOrder("order-0", 3);

        foodOrderingSystem.orderFood("order-1", "restaurant-2", "food-0");
        foodOrderingSystem.rateOrder("order-1", 1);

        foodOrderingSystem.orderFood("order-2", "restaurant-1", "food-0");
        foodOrderingSystem.rateOrder("order-2", 3);

        foodOrderingSystem.orderFood("order-3", "restaurant-2", "food-0");
        foodOrderingSystem.rateOrder("order-3", 5);

        foodOrderingSystem.orderFood("order-4", "restaurant-0", "food-0");
        foodOrderingSystem.rateOrder("order-4", 3);

        foodOrderingSystem.orderFood("order-5", "restaurant-1", "food-0");
        foodOrderingSystem.rateOrder("order-5", 4);

        foodOrderingSystem.orderFood("order-6", "restaurant-0", "food-1");
        foodOrderingSystem.rateOrder("order-6", 2);

        foodOrderingSystem.orderFood("order-7", "restaurant-0", "food-1");
        foodOrderingSystem.rateOrder("order-7", 2);

        foodOrderingSystem.orderFood("order-8", "restaurant-1", "food-0");
        foodOrderingSystem.rateOrder("order-8", 2);

        foodOrderingSystem.orderFood("order-9", "restaurant-1", "food-0");
        foodOrderingSystem.rateOrder("order-9", 4);


        System.out.println(foodOrderingSystem.getTopRestaurantsByFood("food-0"));
        System.out.println(foodOrderingSystem.getTopRestaurantsByFood("food-1"));
        System.out.println(foodOrderingSystem.getTopRatedRestaurants());


    }
}
