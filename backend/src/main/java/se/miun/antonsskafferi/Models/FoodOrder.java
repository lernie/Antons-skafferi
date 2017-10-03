package se.miun.antonsskafferi.Models;

import java.sql.Timestamp;

public class FoodOrder {
    private int id; //PK
    private String modification;
    private int foodId; //FK
    private int diningTableOrderId; //FK
    private int orderStatusId; //FK
    private Timestamp ready;
    private Timestamp created;
    private Timestamp delivered;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public int getFoodId() { return foodId; }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getDiningTableOrderId() {
        return diningTableOrderId;
    }

    public void setDiningTableOrderId(int diningTableOrderId) {
        this.diningTableOrderId = diningTableOrderId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Timestamp getReady() {
        return ready;
    }

    public void setReady(Timestamp ready) {
        this.ready = ready;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getDelivered() {
        return delivered;
    }

    public void setDelivered(Timestamp delivered) {
        this.delivered = delivered;
    }
}
