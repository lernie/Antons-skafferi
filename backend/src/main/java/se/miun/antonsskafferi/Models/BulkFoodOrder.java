package se.miun.antonsskafferi.Models;

import java.util.List;

public class BulkFoodOrder {
    List<Integer> foodOrderIds;
    FoodOrder foodOrder;

    public List<Integer> getFoodOrderIds() {
        return foodOrderIds;
    }

    public void setFoodOrderIds(List<Integer> foodOrderIds) {
        this.foodOrderIds = foodOrderIds;
    }

    public FoodOrder getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }
}
