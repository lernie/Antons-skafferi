package se.miun.antonsskafferi.Models;

public class InventoryItem {
    private int ingredientId = -1;
    private int amount = -1;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
