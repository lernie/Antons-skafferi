package se.miun.antonsskafferi.Models;

public class FoodOrderDelete {
    private int count, diningTableId, foodId;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(int diningTableId) {
        this.diningTableId = diningTableId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
